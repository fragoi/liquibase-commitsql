package liquibase.sqlgenerator.ext;

import liquibase.statement.core.LockDatabaseChangeLogStatement;

/**
 * @author fra
 */
public class CommitLockGenerator extends
        AbstractCommitGenerator<LockDatabaseChangeLogStatement> {

    @Override
    protected boolean isRunInTransaction(
            LockDatabaseChangeLogStatement statement) {
        return true;
    }

}
