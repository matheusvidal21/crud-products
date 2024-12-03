package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderDTO(
        @NotBlank(message = "Code is required and cannot be blank.")
        String codigo,

        @NotNull(message = "Client ID is required.")
        Long clientId,

        @NotNull(message = "Products IDs are required.")
        @Size(min = 1, message = "At least one product ID must be provided.")
        List<Long> productsIds
) {
}
