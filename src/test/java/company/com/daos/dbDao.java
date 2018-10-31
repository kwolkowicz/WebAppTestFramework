package company.com.daos;

import company.com.db.DaoBase;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

@UseStringTemplate3StatementLocator
public interface dbDao extends DaoBase {
    //@formatter:off
    //language=Oracle
    @SqlQuery("SELECT DISTINCT STATE "
            + "FROM SOURCE_METADATA "
            + "WHERE FME_JOB_ID = :jobId ")
    //@formatter:on
    String findJobStatus(@Bind("jobId") int jobId);
}