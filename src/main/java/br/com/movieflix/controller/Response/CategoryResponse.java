package br.com.movieflix.controller.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CategoryResponse(@Schema(type = "integer", format = "int64", description = "Código da categoria")
                               Long id,
                               @Schema(type = "string", description = "Nome da categoria", example = "Ação")
                               String name) {
}
