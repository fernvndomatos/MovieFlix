package br.com.movieflix.controller.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(@Schema(type = "integer", format = "int64", description = "Código do filme")
                            Long id,
                            @Schema(type = "string", description = "Nome do filme")
                            String title,
                            @Schema(type = "string", description = "Descrição do filme")
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            @Schema(type = "string", format = "date", description = "Data de lançamento do filme. ex: '25/04/2010'")
                            LocalDate releaseDate,
                            @Schema(type = "number", format = "double", description = "Score do filme. ex: 8.6")
                            double rating,
                            @Schema(description = "Lista de categoria")
                            List<CategoryResponse> categories,
                            @Schema(description = "Lista de serviço de streaming")
                            List<StreamingResponse> streamings) {
}
