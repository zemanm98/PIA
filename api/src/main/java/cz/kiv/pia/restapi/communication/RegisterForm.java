package cz.kiv.pia.restapi.communication;

public class RegisterForm {
    /**
     * name of a new registering user.
     */
    private String name;
    /**
     * email of a new registering user.
     */
    private String email;
    /**
     * password of a new registering user.
     */
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
