package cz.kiv.pia.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Represents a single user of the service.
 */
public class User{
    /**
     * Unique identifier.
     */
    private final String id;
    /**
     * Full name of the user.
     */
    private final String userName;

    private final String email;
    /**
     * Role of the user.
     */
    private final Role role;

    private final String password;

    public User(String id, String userName, String email, Role role, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword(){return password;}

    public Role getRole() {
        return role;
    }

    public String getEmail(){return email;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", role=" + role +
                '}';
    }

    public enum Role {
        /**
         * Regular user
         */
        REGULAR,
        /**
         * Serviceman, can do everything that regular users but also maintains bikes
         */
        SERVICEMAN,
    }
}
