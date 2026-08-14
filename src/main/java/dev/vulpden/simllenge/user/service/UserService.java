package dev.vulpden.simllenge.user.service;

import dev.vulpden.simllenge.user.model.User;

public interface UserService {
    User getUserByEmail(String username);
}
