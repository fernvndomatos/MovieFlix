package br.com.movieflix.controller.Response;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email){
}
