package api.pojo.registerPojo;

import api.pojo.userPojo.UserCreateData;

public class Register {

    private String email;
    private String password;

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static class SuccessUserCreate extends UserCreateData {
        private String id;
        private String createdAt;
    
        public SuccessUserCreate() {
        }
    
        public SuccessUserCreate(String job, String name, String id, String createdAt) {
            super(job, name);
            this.id = id;
            this.createdAt = createdAt;
        }
    
        public String getId() {
            return id;
        }
    
        public String getCreatedAt() {
            return createdAt;
        }
    }
}
