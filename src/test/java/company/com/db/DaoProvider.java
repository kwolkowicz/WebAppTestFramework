package company.com.db;

import company.com.daos.FmeSourceMetaDataDao;

public enum DaoProvider {

    DAO_PROVIDER;

    private final FmeSourceMetaDataDao fmeSourceMetaDataDao;

    DaoProvider() {
        DbManager dbManager = new DbManager();
        fmeSourceMetaDataDao = dbManager.getFmeSourceMetaDataDao();
        dbManager.close();
    }

    public FmeSourceMetaDataDao getFmeSourceMetaDataDao() {
        return fmeSourceMetaDataDao;
    }

}
