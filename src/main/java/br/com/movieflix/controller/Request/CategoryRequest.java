package br.com.movieflix.controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "Nome da categoria é obrigatório.")
                              @Schema(type = "string", description = "Nome da categoria", example = "Ação")
                              @NotEmpty(message = "Nome da categoria é obrigatório.")
                              String name) {
}
