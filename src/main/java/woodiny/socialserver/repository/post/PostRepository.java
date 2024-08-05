package woodiny.socialserver.repository.post;

import org.springframework.stereotype.Repository;
import woodiny.socialserver.model.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    long save(Post post);
    void update(Post post);
    Optional<Post> findById(long postId);
    List<Post> findAll(long userId);
}
