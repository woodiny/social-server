package woodiny.socialserver.model.post;

import java.time.LocalDateTime;

public class Post {
    private final Long seq;
    private final String userId;
    private String contents;
    private int likes;
    private boolean likesOfMe;
    private int comments;
    private final Writer writer;
    private final LocalDateTime createAt;

    public Post(Long seq, String userId, String contents, int likes, boolean likesOfMe, int comments, Writer writer, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.contents = contents;
        this.likes = likes;
        this.likesOfMe = likesOfMe;
        this.comments = comments;
        this.writer = writer;
        this.createAt = createAt;
    }
}
