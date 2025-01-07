package br.ufrn.imd.web.dtos;

import lombok.NonNull;

public record JwtDTO(
        @NonNull
        String token,
        String type
) {
        // Inicializa o tipo do token como "Bearer" por padrão
        public JwtDTO(String token) {
                this(token, "Bearer");
        }
}
