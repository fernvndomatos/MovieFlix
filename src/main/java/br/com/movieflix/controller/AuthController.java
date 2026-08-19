package br.com.movieflix.controller;

import br.com.movieflix.config.TokenService;
import br.com.movieflix.controller.Request.LoginRequest;
import br.com.movieflix.controller.Request.UserRequest;
import br.com.movieflix.controller.Response.LoginResponse;
import br.com.movieflix.controller.Response.UserResponse;
import br.com.movieflix.entity.User;
import br.com.movieflix.exception.UsernameOrPasswordInvalidException;
import br.com.movieflix.mapper.UserMapper;
import br.com.movieflix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Operation(summary = "Registar usuario", description = "Método responsavel por realizar salvamento de um novo ususario")
    @SecurityRequirements()
    @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }

    @Operation(summary = "Autenticar usuário", description = "Realiza login e retorna token JWT")
    @SecurityRequirements()
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse(responseCode = "400", description = "Usuário ou senha inválido")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Usuario ou senha inválido.");
        }
    }
}
