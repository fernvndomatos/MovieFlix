package br.com.movieflix.controller.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginResponse(@Schema(type = "string", description = "Token JWT de autenticação")
                            String token) {
}
