package joaovitorlopes.com.github.forum_hub_api.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email
        String email,
        @NotBlank
        String password
) {
}
