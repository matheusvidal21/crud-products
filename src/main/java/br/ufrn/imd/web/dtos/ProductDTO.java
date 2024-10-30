package br.ufrn.imd.web.dtos;

import java.time.LocalDate;

public record ProductDTO (
        String name,
        String brand,
        LocalDate manufacturingDate,
        LocalDate expirationDate,
        String category
) {
}
