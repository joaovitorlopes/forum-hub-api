package joaovitorlopes.com.github.forum_hub_api.domain.topic.dto;

import joaovitorlopes.com.github.forum_hub_api.domain.topic.Status;

public record UpdateTopicDTO(
        String title,
        String text,
        Status status
) {
}
