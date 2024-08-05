package woodiny.socialserver.model.post;

import lombok.Getter;

import java.time.LocalDateTime;

public class Post {
    private Long seq;

    @Getter
    private final Long userId;

    @Getter
    private String contents;

    @Getter
    private int likes;
    private boolean likesOfMe;

    @Getter
    private int comments;
    private final Writer writer;

    @Getter
    private LocalDateTime createAt;

    public Post(Long seq, Long userId, String contents, int likes, boolean likesOfMe, int comments, Writer writer, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.contents = contents;
        this.likes = likes;
        this.likesOfMe = likesOfMe;
        this.comments = comments;
        this.writer = writer;
        this.createAt = createAt;
    }

    public Post(Long seq, Long userId, String contents, int likes, Writer writer, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.contents = contents;
        this.likes = likes;
        this.writer = writer;
        this.createAt = createAt;
    }

    public Post(Long userId, String contents, Writer writer) {
        this.userId = userId;
        this.contents = contents;
        this.writer = writer;
        this.createAt = LocalDateTime.now();
    }
}
