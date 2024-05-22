package woodiny.socialserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import woodiny.socialserver.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("seq"),
                    rs.getString("email"),
                    rs.getString("passwd"),
                    rs.getInt("login_count"),
                    rs.getObject("last_login_at", LocalDateTime.class),
                    rs.getObject("create_at", LocalDateTime.class)
            );
        }
    }
}
