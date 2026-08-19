package br.com.movieflix.controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserRequest(@Schema(type = "string", description = "Nome do usuário", example = "Fernando Matos")
                          @NotEmpty(message = "Nome é obrigatório.")
                          String name,
                          @Schema(type = "string", description = "E-mail do usuário", example = "fernando@email.com")
                          @NotEmpty(message = "Email é obrigatório.")
                          String email,
                          @Schema(type = "string", description = "Senha do usuário.")
                          @NotEmpty(message = "Senha é obrigatória.")
                          String password) {
}
