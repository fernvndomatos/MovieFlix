package br.com.movieflix.controller.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserResponse(@Schema(type = "integer", format = "int64", description = "Código do usuário")
                           Long id,
                           @Schema(type = "string", description = "Nome do usuário", example = "Fernando Matos")
                           String name,
                           @Schema(type = "string", description = "E-mail do usuário", example = "fernando@email.com")
                           String email){
}
