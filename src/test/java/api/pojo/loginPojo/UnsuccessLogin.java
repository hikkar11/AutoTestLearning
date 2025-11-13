package api.pojo.loginPojo;

public class UnsuccessLogin {

    private String error;

    public UnsuccessLogin() {
    }

    public UnsuccessLogin(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
