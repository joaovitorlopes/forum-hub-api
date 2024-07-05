package joaovitorlopes.com.github.forum_hub_api.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentDTO(@NotBlank String text) {
}
