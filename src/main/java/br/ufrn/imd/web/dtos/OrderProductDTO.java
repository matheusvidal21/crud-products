package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotNull;

public record OrderProductDTO(
        @NotNull(message = "Order ID is required.")
        Long orderId,

        @NotNull(message = "Product ID is required.")
        Long productId
) {
}
