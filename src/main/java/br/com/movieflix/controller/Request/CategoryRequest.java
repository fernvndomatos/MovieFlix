package br.com.movieflix.controller.Request;

import lombok.Builder;

@Builder
public record CategoryRequest(String name) {
}
