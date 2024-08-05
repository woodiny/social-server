package woodiny.socialserver.repository.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import woodiny.socialserver.model.post.Post;
import woodiny.socialserver.model.post.Writer;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.util.ConvertUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static woodiny.socialserver.util.ConvertUtil.*;

@Slf4j
@Repository
public class JdbcPostRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into posts" +
                    " (user_seq, contents, like_count, comment_count, create_at)" +
                    " values (?, ?, ?, ?, ?)",
                    new String[]{"seq"});
            ps.setLong(1, post.getUserId());
            ps.setString(2, post.getContents());
            ps.setInt(3, post.getLikes());
            ps.setInt(4, post.getComments());
            ps.setTimestamp(5, timestampOf(post.getCreateAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key == null ? -1 : key.longValue();
    }

    @Override
    public void update(Post post) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("update posts" +
                    " set like_count = ?, contents = ?, comment_count = ?" +
                    " where seq = ?");
            ps.setInt(1, post.getLikes());
            ps.setString(2, post.getContents());
            ps.setInt(3, post.getComments());
            return ps;
        });
    }

    @Override
    public Optional<Post> findById(long postId) {
        try {
            Post post =  jdbcTemplate.queryForObject(
                    "select * from posts where seq = ?",
                    new PostRowMapper(),
                    postId
            );
            return Optional.ofNullable(post);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Post not found. postId: {}", postId);
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll(long userId) {
        return jdbcTemplate.query(
                "select p.*, u.email from p join users u on p.user_seq = u.seq" +
                        " where p.user_seq = ?" +
                        " order by p.seq desc",
                new PostRowMapper(),
                userId
        );
    }

    private static class PostRowMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Post(
                    rs.getLong("seq"),
                    rs.getLong("user_seq"),
                    rs.getString("contents"),
                    rs.getInt("like_count"),
                    new Writer(new Email(rs.getString("email"))),
                    localDateTimeOf(rs.getTimestamp("create_at"))
            );
        }
    }
}
