package br.com.movieflix.controller.Response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
