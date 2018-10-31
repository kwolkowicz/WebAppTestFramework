package company.com.helpers;

public class TestUser {
    private final String login;
    private final String password;

    public TestUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
