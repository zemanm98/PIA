package cz.kiv.pia.restapi.vo;

import cz.kiv.pia.domain.Location;

/**
 * Value Object of User for presentation layer.
 * @param id - id of this user
 * @param name - name of this user
 * @param role - role of this user.
 */
public record UserVO(
        String id,
        String name,

        String role
) {
    public UserVO(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
