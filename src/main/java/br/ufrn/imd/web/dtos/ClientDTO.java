package br.ufrn.imd.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClientDTO (
        @NotBlank(message = "Name is required and cannot be blank.")
        String name,

        @NotBlank(message = "CPF is required and cannot be blank.")
        @CPF(message = "CPF is invalid.")
        String cpf,

        @NotBlank(message = "Gender is required and cannot be blank.")
        @Pattern(regexp = "Male|Female", message = "Gender must be one of: Male, Female.")
        String gender,

        @NotNull(message = "Birth date is required.")
        @PastOrPresent(message = "Birth date must be in the past or today.")
        LocalDate birthDate
) {
}

