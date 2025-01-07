package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Username is required and cannot be blank.")
        String username,

        @NotBlank(message = "Password is required and cannot be blank.")
        String password
) {
}
