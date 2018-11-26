package liquibase.sqlgenerator.ext;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.executor.ExecutorService;
import liquibase.executor.LoggingExecutor;
import liquibase.sql.Sql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.SqlGeneratorFactory;
import liquibase.sqlgenerator.core.AbstractSqlGenerator;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.RawSqlStatement;

/**
 * @author fra
 */
abstract class AbstractCommitGenerator<T extends SqlStatement> extends
        AbstractSqlGenerator<T> {

    public static Sql[] add(
            Sql[] sql,
            SqlStatement statement,
            Database database) {
        return merge(
                sql,
                SqlGeneratorFactory.getInstance()
                        .generateSql(statement, database));
    }

    public static Sql[] merge(Sql[]... matrix) {
        int length = 0;
        for (Sql[] array : matrix) {
            length += array.length;
        }
        Sql[] sql = new Sql[length];
        int index = 0;
        for (Sql[] array : matrix) {
            System.arraycopy(array, 0, sql, index, array.length);
            index += array.length;
        }
        return sql;
    }

    public static SqlStatement commitStatement() {
        return new RawSqlStatement("COMMIT");
    }

    public static boolean isWritingSQL(Database database) {
        return ExecutorService.getInstance()
                .getExecutor(database) instanceof LoggingExecutor;
    }

    @Override
    public int getPriority() {
        return PRIORITY_DEFAULT + 1;
    }

    @Override
    public ValidationErrors validate(
            T statement,
            Database database,
            SqlGeneratorChain sqlGeneratorChain) {
        return sqlGeneratorChain.validate(statement, database);
    }

    @Override
    public Sql[] generateSql(
            T statement,
            Database database,
            SqlGeneratorChain sqlGeneratorChain) {
        /* array from MarkChangeSetRanGenerator */
        Sql[] sql = sqlGeneratorChain.generateSql(statement, database);
        if (sql.length != 0
                && isRunInTransaction(statement)
                && isWritingSQL(database)) {
            sql = add(sql, commitStatement(), database);
        }
        return sql;
    }

    protected abstract boolean isRunInTransaction(T statement);

}
