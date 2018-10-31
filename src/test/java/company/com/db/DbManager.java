package company.com.db;

import company.com.daos.dbDao;
import org.apache.commons.dbcp.BasicDataSource;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

public class DbManager {
    private final DBI dbi;
    private final dbDao dbDao;


    public DbManager() {
        DataSource dataSource = initDataSource();
        dbi = new DBI(dataSource);

        dbi.registerMapper(new CustomBeanMapperFactory());

        dbDao = createProxy(dbDao.class);
    }

    private static DataSource initDataSource() {
        String url = getEnvVarOrDefault("DB_LOCAL_URL", "HERE PROVIDE DB DETAILS");
        String username = getEnvVarOrDefault("DB_LOCAL_USERNAME", "user");
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

    public dbDao getDbDao() {
        return dbDao;
    }


    public void close() {
        Stream.of(dbDao)
                .forEach(dao -> dao.close(dbi));

    }

    private <T> T createProxy(Class<T> daoInterface) {
        T daoObject = dbi.onDemand(daoInterface);

        Object newProxyInstance = Proxy.newProxyInstance(daoInterface.getClassLoader(), new Class<?>[]{daoInterface},
                (proxy, method, args) -> method.invoke(daoObject, args));
        return (T) newProxyInstance;
    }

}
