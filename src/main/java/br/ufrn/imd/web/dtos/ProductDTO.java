package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ProductDTO (
        @NotBlank(message = "Name is required and cannot be blank.")
        String name,

        @NotBlank(message = "Brand is required and cannot be blank.")
        String brand,

        @NotNull(message = "Manufacturing date is required.")
        @PastOrPresent(message = "Manufacturing date must be in the past or today.")
        LocalDate manufacturingDate,

        @NotNull(message = "Expiration date is required.")
        LocalDate expirationDate,

        @NotBlank(message = "Category is required and cannot be blank.")
        @Pattern(regexp = "Food|Cosmetics|Cleaning|Personal Care", message = "Category must be one of: Food, Cosmetics, Cleaning, Personal Care.")
        String category
) {
}
