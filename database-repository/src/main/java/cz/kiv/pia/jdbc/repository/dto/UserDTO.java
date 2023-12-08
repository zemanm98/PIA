package cz.kiv.pia.jdbc.repository.dto;

public record UserDTO(
        int id,

        String name,

        String emailAdress,

        String role
) { }
