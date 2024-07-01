package woodiny.socialserver.repository.user;

import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public List<User> findAll();
    public Optional<User> findBySeq(Long seq);
    public Optional<User> findByEmail(Email email);
    public long save(User user);
    public void update(User user);
}
