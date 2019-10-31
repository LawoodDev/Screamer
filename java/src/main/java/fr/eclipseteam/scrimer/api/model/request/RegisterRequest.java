package fr.eclipseteam.scrimer.api.model.request;

public class RegisterRequest {

    private String email;
    private String password;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
