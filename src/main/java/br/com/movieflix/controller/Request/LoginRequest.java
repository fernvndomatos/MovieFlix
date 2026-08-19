package br.com.movieflix.controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LoginRequest(@Schema(type = "string", description = "E-mail do usuário", example = "usuario@email.com")
                           @NotEmpty(message = "Email é obrigatório.")
                           String email,
                           @Schema(type = "string", description = "Senha do usuário")
                           @NotEmpty(message = "Senha é obrigatória.")
                           String password) {
}
