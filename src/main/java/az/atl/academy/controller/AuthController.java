package az.atl.academy.controller;

import az.atl.academy.model.dto.JwtAuthenticationResponse;
import az.atl.academy.model.dto.SignInRequest;
import az.atl.academy.model.dto.SignUpRequest;
import az.atl.academy.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "User Registration")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUpRequest(@RequestBody @Valid SignUpRequest request){
        return authenticationService.signUp(request);
    }

    @Operation(summary = "User Authorization")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signInRequest(@RequestBody @Valid SignInRequest request){
        return authenticationService.signIn(request);
    }
}
