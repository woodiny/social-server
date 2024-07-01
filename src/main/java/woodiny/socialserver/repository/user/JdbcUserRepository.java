package woodiny.socialserver.repository.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static woodiny.socialserver.util.ConvertUtil.*;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "select * from users",
                new UserRowMapper()
        );
    }

    @Override
    public Optional<User> findBySeq(Long seq) {
        try {
            User user = jdbcTemplate.queryForObject(
                    "select * from users where seq = ?",
                    new UserRowMapper(),
                    seq
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            log.warn("User not found. seq: {}", seq);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        try {
            User user = jdbcTemplate.queryForObject(
                    "select * from users where email = ?",
                    new UserRowMapper(),
                    email.getAddress()
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            log.warn("User not found. email: {}", email.getAddress());
            return Optional.empty();
        }
    }

    @Override
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

    @Override
    public void update(User user) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("UPDATE users " +
                            " SET email = ?, passwd = ?, login_count = ?, last_login_at = ? " +
                            " where seq = ?");
            ps.setString(1, user.getEmail().getAddress());
            ps.setString(2, user.getPasswd());
            ps.setInt(3, user.getLoginCount());
            ps.setTimestamp(4, timestampOf(user.getLastLoginAt()));
            ps.setLong(5, user.getSeq());
            return ps;
        });
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
