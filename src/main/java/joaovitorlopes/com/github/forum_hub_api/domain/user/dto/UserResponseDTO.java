package joaovitorlopes.com.github.forum_hub_api.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import joaovitorlopes.com.github.forum_hub_api.domain.user.User;
import joaovitorlopes.com.github.forum_hub_api.domain.user.UserRoles;

public record UserResponseDTO(
        Long id,
        String name,

        @JsonProperty("is_active")
        boolean isActive,

        UserRoles role) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.isActive(), user.getRole());
    }
}
