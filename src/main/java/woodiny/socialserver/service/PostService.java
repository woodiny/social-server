package woodiny.socialserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woodiny.socialserver.model.post.Post;
import woodiny.socialserver.repository.post.PostRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Long write(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post modify(Post post) {
        postRepository.update(post);
        return post;
    }

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findAll(Long userId) {
        return postRepository.findAll(userId);
    }
}
