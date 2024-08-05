package woodiny.socialserver.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import woodiny.socialserver.model.post.Post;
import woodiny.socialserver.model.post.Writer;
import woodiny.socialserver.model.user.Email;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    public void write() {
        String contents = "test contents";
        Writer writer = new Writer(new Email("tester1@gmail.com"));
        Post post = new Post(1L, contents, writer);

        Long postId = postService.write(post);

        assertThat(post).isNotNull();
        assertThat(postId).isNotNull();
        assertThat(postId).isGreaterThan(0);
    }
}
