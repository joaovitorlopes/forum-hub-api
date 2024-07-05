package joaovitorlopes.com.github.forum_hub_api.domain.course.dto;

import jakarta.validation.constraints.NotBlank;
import joaovitorlopes.com.github.forum_hub_api.domain.course.Categories;

public record CreateCourseDTO(@NotBlank String name, Categories category) {

}
