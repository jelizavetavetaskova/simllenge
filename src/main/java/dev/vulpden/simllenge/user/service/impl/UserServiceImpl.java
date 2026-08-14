package dev.vulpden.simllenge.user.service.impl;

import dev.vulpden.simllenge.user.model.User;
import dev.vulpden.simllenge.user.repo.UserRepo;
import dev.vulpden.simllenge.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User does not exist"));
    }
}
