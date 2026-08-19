package br.com.movieflix.controller.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(@Schema(type = "string", description = "Nome do filme")
                           @NotEmpty(message = "Titulo do filme é obrigatório.")
                           String title,
                           @Schema(type = "string", description = "Descrição do filme")
                           String description,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           @Schema(type = "string", format = "date", description = "Data de lançamento do filme. ex: '25/04/2010'")
                           LocalDate releaseDate,
                           @Schema(type = "number", format = "double", description = "Score do filme. ex: 8.6")
                           double rating,
                           @Schema(description = "Lista de códigos de categoria")
                           List<Long> categories,
                           @Schema(description = "Lista de códigos de serviço de streaming")
                           List<Long> streamings) {
}
