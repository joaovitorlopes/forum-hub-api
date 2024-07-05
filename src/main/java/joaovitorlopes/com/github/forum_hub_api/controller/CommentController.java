package joaovitorlopes.com.github.forum_hub_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import joaovitorlopes.com.github.forum_hub_api.domain.comment.CommentRepository;
import joaovitorlopes.com.github.forum_hub_api.domain.comment.dto.CommentResponseDTO;
import joaovitorlopes.com.github.forum_hub_api.infra.exceptions.Forbidden;
import joaovitorlopes.com.github.forum_hub_api.infra.exceptions.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@SecurityRequirement(name = "bearer-key")
public class CommentController {

    private final CommentRepository repository;

    public CommentController(CommentRepository commentRepository) {
        this.repository = commentRepository;
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long commentId) {
        var comment = repository.findById(commentId).orElseThrow(() -> new NotFound("Comment not found"));
        return ResponseEntity.ok(new CommentResponseDTO(comment));
    }

    @DeleteMapping("/{commentId}")
    @Transactional
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        var userId = authentication.getCredentials().toString();
        var comment = repository.findById(commentId).orElseThrow(() -> new NotFound("Comment not found"));
        if (!comment.getUser().getId().toString().equals(userId) &&
                authentication.getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new Forbidden("You do not have permission to perform this action");
        }

        repository.deleteById(commentId);
        return ResponseEntity.noContent().build();
    }
}
