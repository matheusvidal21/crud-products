package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDTO(
        @NotNull(message = "User id is required")
        Long userId,

        @NotBlank(message = "Password is required")
        String password
) {
}
