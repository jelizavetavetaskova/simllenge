package dev.vulpden.simllenge.user.service;

import dev.vulpden.simllenge.user.dto.UserDto;

public interface UserService {
    UserDto getUserByUsername(String username);
}
