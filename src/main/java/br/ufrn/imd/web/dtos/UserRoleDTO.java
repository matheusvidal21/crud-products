package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRoleDTO(
        @NotNull(message = "User ID is mandatory")
        Long userId,

        @NotBlank(message = "Role ID is mandatory")
        String role
) {
}
