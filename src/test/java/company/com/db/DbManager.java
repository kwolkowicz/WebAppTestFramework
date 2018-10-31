package company.com.db;

import company.com.daos.FmeSourceMetaDataDao;
import org.apache.commons.dbcp.BasicDataSource;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

public class DbManager {
    private final DBI dbi;
    private final FmeSourceMetaDataDao fmeSourceMetaDataDao;


    public DbManager() {
        DataSource dataSource = initDataSource();
        dbi = new DBI(dataSource);

        dbi.registerMapper(new CustomBeanMapperFactory());

        fmeSourceMetaDataDao = createProxy(FmeSourceMetaDataDao.class);
    }

    private static DataSource initDataSource() {
        String url = getEnvVarOrDefault("DB_LOCAL_URL", "jdbc:postgresql://Densimg01st:5432/ingest?autoReconnect=true");
        String username = getEnvVarOrDefault("DB_LOCAL_USERNAME", "ingest");
        String password = getEnvVarOrDefault("DB_LOCAL_PASSWORD", "password");
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }

    private static String getEnvVarOrDefault(String env, String defaultValue) {
        String envValue = System.getenv(env);

        return envValue == null ? defaultValue : envValue;
    }

    public FmeSourceMetaDataDao getFmeSourceMetaDataDao() {
        return fmeSourceMetaDataDao;
    }


    public void close() {
        Stream.of(fmeSourceMetaDataDao)
                .forEach(dao -> dao.close(dbi));

    }

    private <T> T createProxy(Class<T> daoInterface) {
        T daoObject = dbi.onDemand(daoInterface);

        Object newProxyInstance = Proxy.newProxyInstance(daoInterface.getClassLoader(), new Class<?>[]{daoInterface},
                (proxy, method, args) -> method.invoke(daoObject, args));
        return (T) newProxyInstance;
    }

}
