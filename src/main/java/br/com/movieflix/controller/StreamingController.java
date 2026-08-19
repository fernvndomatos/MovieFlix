package br.com.movieflix.controller;

import br.com.movieflix.controller.Request.StreamingRequest;
import br.com.movieflix.controller.Response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.repository.StreamingRepository;
import br.com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    private final StreamingRepository streamingRepository;

    @Operation(summary = "Buscar streaming", description = "Método responsavel por retornar todos os streamings cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os streamings cadastrados",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreamings(){
        List<StreamingResponse> streamings = streamingRepository.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamings);
    }

    @Operation(summary = "Salvar streaming", description = "Método responsavel por realizar salvamento de um novo streaming",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Streaming salvo com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping()
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @Operation(summary = "Buscar streaming por id", description = "Método responsavel por buscar streaming por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streaming encontrado com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse (streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar streamings por id", description = "Método responsavel por deletar streamings por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streaming deletado com sucesso", content = @Content())
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id){
        streamingService.deleteStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
