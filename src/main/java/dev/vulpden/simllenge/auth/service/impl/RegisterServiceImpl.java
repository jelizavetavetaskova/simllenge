package dev.vulpden.simllenge.auth.service.impl;

import dev.vulpden.simllenge.auth.dto.RegisterDto;
import dev.vulpden.simllenge.auth.service.RegisterService;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.user.dto.UserDto;
import dev.vulpden.simllenge.user.model.User;
import dev.vulpden.simllenge.user.model.enums.Authority;
import dev.vulpden.simllenge.user.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final MapperService mapperService;

    public RegisterServiceImpl(UserRepo userRepo, PasswordEncoder encoder, MapperService mapperService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.mapperService = mapperService;
    }

    @Override
    public UserDto register(RegisterDto userData) {
        if (userRepo.existsByUsername(userData.getUsername())) {
            throw new IllegalArgumentException("Username is taken");
        }
        if (userRepo.existsByEmail(userData.getEmail())) {
            throw new IllegalArgumentException("User with this email is already registered");
        }
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setAuthority(Authority.USER);
        user.setPasswordHash(encoder.encode(userData.getPassword()));

        return mapperService.userToDto(userRepo.save(user));
    }
}
