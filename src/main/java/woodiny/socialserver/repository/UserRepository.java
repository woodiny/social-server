package woodiny.socialserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import woodiny.socialserver.dto.UserRegisterRequest;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;
import woodiny.socialserver.util.ConvertUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static woodiny.socialserver.util.ConvertUtil.*;

@Slf4j
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "select * from users",
                new UserRowMapper()
        );
    }

    public User find(Long seq) {
        return jdbcTemplate.queryForObject(
                "select * from users where seq = ?",
                new UserRowMapper(),
                seq
        );
    }

    public long save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users" +
                    " (email, passwd, login_count, last_login_at, create_at) " +
                    " VALUES (?,?,?,?,?)",
                    new String[]{"seq"});
            ps.setString(1, user.getEmail().getAddress());
            ps.setString(2, user.getPasswd());
            ps.setInt(3, user.getLoginCount());
            ps.setTimestamp(4, timestampOf(user.getLastLoginAt()));
            ps.setTimestamp(5, timestampOf(user.getCreateAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key == null ? -1 : key.longValue();
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("seq"),
                    new Email(rs.getString("email")),
                    rs.getString("passwd"),
                    rs.getInt("login_count"),
                    rs.getObject("last_login_at", LocalDateTime.class),
                    rs.getObject("create_at", LocalDateTime.class)
            );
        }
    }
}
