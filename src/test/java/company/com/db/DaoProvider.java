package company.com.db;

import company.com.daos.dbDao;

public enum DaoProvider {

    DAO_PROVIDER;

    private final dbDao dbDao;

    DaoProvider() {
        DbManager dbManager = new DbManager();
        dbDao = dbManager.getDbDao();
        dbManager.close();
    }

    public dbDao getDbDao() {
        return dbDao;
    }

}
