package api.pojo.userPojo;

public class UpdateUserResponce extends UpdateUserData {

    private String updatedAt;

    public UpdateUserResponce() {
    }

    public UpdateUserResponce(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
