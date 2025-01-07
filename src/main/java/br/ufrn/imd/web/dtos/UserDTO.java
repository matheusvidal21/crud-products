package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.*;

public record UserDTO(
        @NotBlank(message = "Username is required and cannot be blank.")
        @Size(max = 20, message = "Username must have at most 20 characters.")
        String username,

        @Email(message = "Email must be valid.")
        @Size(max = 50, message = "Email must have at most 50 characters.")
        String email,

        @NotBlank(message = "Password is required and cannot be blank.")
        @Size(min = 6, max = 20, message = "Password must have between 6 and 20 characters.")
        String password
) {
}
