package liquibase.sqlgenerator.ext;

import liquibase.statement.core.RemoveChangeSetRanStatusStatement;

/**
 * @author fra
 */
public class CommitUnmarkGenerator extends
        AbstractCommitGenerator<RemoveChangeSetRanStatusStatement> {

    @Override
    protected boolean isRunInTransaction(
            RemoveChangeSetRanStatusStatement statement) {
        return statement.getChangeSet().isRunInTransaction();
    }

}
