package dev.vulpden.simllenge.user.repo;

import dev.vulpden.simllenge.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
}
