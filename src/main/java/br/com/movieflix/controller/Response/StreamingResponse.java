package br.com.movieflix.controller.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse(@Schema(type = "integer", format = "int64", description = "Código do serviço de streaming")
                                Long id,
                                @Schema(type = "string", description = "Nome do serviço de streaming", example = "Netflix")
                                String name) {
}
