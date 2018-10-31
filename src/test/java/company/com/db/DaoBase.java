package company.com.db;

import org.skife.jdbi.v2.DBI;

public interface DaoBase {
    default void close(DBI dbi) {
        dbi.close(this);
    }
}
