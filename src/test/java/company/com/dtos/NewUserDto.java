package company.com.dtos;

public class NewUserDto {

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private int postalCode;
    private String countryName;
    private String userName;
    private String password;

    public NewUserDto(String firstName, String lastName, int phoneNumber, String email, String address,
                      String city, String state, int postalCode, String countryName, String userName, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.countryName = countryName;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public NewUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public NewUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public NewUserDto setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NewUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public NewUserDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public NewUserDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public NewUserDto setState(String state) {
        this.state = state;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public NewUserDto setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public NewUserDto setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public NewUserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public NewUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
