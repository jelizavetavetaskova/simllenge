package dev.vulpden.simllenge.auth.controller;

import dev.vulpden.simllenge.auth.dto.RegisterDto;
import dev.vulpden.simllenge.auth.service.RegisterService;
import dev.vulpden.simllenge.user.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegisterService registerService;

    public AuthController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterDto registerDto) {
        UserDto user = registerService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
