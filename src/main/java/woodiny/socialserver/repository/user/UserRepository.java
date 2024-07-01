package woodiny.socialserver.repository.user;

import woodiny.socialserver.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public List<User> findAll();
    public Optional<User> findBySeq(Long seq);
    public long save(User user);
}
