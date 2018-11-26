package liquibase.sqlgenerator.ext;

import liquibase.statement.core.MarkChangeSetRanStatement;

/**
 * @author fra
 */
public class CommitMarkGenerator extends
        AbstractCommitGenerator<MarkChangeSetRanStatement> {

    @Override
    protected boolean isRunInTransaction(MarkChangeSetRanStatement statement) {
        return statement.getChangeSet().isRunInTransaction();
    }

}
