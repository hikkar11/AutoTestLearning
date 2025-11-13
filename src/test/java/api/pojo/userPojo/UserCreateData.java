package api.pojo.userPojo;

public class UserCreateData {
    private String name;
    private String job;

    public UserCreateData() {
    }

    public UserCreateData(String job, String name) {
        this.job = job;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
