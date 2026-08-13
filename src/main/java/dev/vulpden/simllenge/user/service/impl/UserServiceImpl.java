package dev.vulpden.simllenge.user.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.user.dto.UserDto;
import dev.vulpden.simllenge.user.model.User;
import dev.vulpden.simllenge.user.repo.UserRepo;
import dev.vulpden.simllenge.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final MapperService mapperService;

    public UserServiceImpl(UserRepo userRepo, MapperService mapperService) {
        this.userRepo = userRepo;
        this.mapperService = mapperService;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new NoSuchElementException("User does not exist"));

        return mapperService.userToDto(user);
    }
}
