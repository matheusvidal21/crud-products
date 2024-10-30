package br.ufrn.imd.web.dtos;

import java.time.LocalDate;

public record ClientDTO (
        String name,
        String cpf,
        String gender,
        LocalDate birthDate
) {
}
