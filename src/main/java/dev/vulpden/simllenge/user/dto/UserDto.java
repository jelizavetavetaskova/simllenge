package dev.vulpden.simllenge.user.dto;

import dev.vulpden.simllenge.user.model.enums.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private Authority authority;
}
