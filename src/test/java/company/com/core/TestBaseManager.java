package company.com.core;


import company.com.dtos.TestBaseDto;

public class TestBaseManager {
    private static ThreadLocal<TestBaseDto> testBaseThread = new ThreadLocal<>();

    private TestBaseManager() {
    }

    public static synchronized TestBaseDto fetchTestBaseInstance() {
        if (testBaseThread.get() == null) {

            TestBaseDto testBaseDto = new TestBaseDto();
            testBaseDto.setDriver(new SeleniumCore());

            testBaseThread.set(testBaseDto);
        }
        return testBaseThread.get();
    }

    public static synchronized void removeDriver() {
        testBaseThread.get().getDriver().close();
        testBaseThread.remove();
    }
}
