package dev.vulpden.simllenge.auth.service;

import dev.vulpden.simllenge.auth.dto.RegisterDto;
import dev.vulpden.simllenge.user.dto.UserDto;

public interface RegisterService {
    UserDto register(RegisterDto userData);
}
