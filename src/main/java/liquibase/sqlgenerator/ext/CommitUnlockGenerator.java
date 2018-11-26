package liquibase.sqlgenerator.ext;

import liquibase.statement.core.UnlockDatabaseChangeLogStatement;

/**
 * @author fra
 */
public class CommitUnlockGenerator extends
        AbstractCommitGenerator<UnlockDatabaseChangeLogStatement> {

    @Override
    protected boolean isRunInTransaction(
            UnlockDatabaseChangeLogStatement statement) {
        return true;
    }

}
