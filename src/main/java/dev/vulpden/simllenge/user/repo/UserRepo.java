package dev.vulpden.simllenge.user.repo;

import dev.vulpden.simllenge.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);


    boolean existsByUsername(@NotBlank(message = "Username is required") String username);

    boolean existsByEmail(String email);
}
