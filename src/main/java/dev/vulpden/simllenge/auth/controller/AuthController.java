package dev.vulpden.simllenge.auth.controller;

import dev.vulpden.simllenge.auth.dto.LoginDto;
import dev.vulpden.simllenge.auth.dto.RegisterDto;
import dev.vulpden.simllenge.auth.service.RegisterService;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.user.dto.UserDto;
import dev.vulpden.simllenge.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegisterService registerService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final MapperService mapperService;

    public AuthController(RegisterService registerService, AuthenticationManager authenticationManager, UserService userService, MapperService mapperService) {
        this.registerService = registerService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mapperService = mapperService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterDto registerDto) {
        UserDto user = registerService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest httpReq) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            HttpSession session = httpReq.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            UserDto user = mapperService.userToDto(userService.getUserByEmail(auth.getName()));
            return ResponseEntity.ok(user);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or/and password");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me(Authentication auth) {
        UserDto user = mapperService.userToDto(userService.getUserByEmail(auth.getName()));
        return ResponseEntity.ok(user);
    }
}
