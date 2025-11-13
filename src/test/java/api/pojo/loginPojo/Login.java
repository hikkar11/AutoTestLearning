package api.pojo.loginPojo;

public class Login {
    private String email;
    private String password;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getemail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
