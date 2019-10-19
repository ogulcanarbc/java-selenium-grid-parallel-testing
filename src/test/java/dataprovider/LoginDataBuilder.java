package dataprovider;

public class LoginDataBuilder {

    private String casename;
    private String username;
    private String password;

    public LoginDataBuilder withCaseName(String casename) {
        this.casename = casename;
        return this;
    }
  
    public LoginDataBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginDataModel build() {
        LoginDataModel loginData = new LoginDataModel();
        loginData.setCasename(casename);
        loginData.setUsername(username);
        loginData.setPassword(password);
        return loginData;
    }

}
