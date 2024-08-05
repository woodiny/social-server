package woodiny.socialserver.repository.user;

import woodiny.socialserver.model.user.ConnectedUser;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findBySeq(Long seq);
    Optional<User> findByEmail(Email email);
    long save(User user);
    void update(User user);
    List<ConnectedUser> findAllConnectedUser(long seq);
    List<Long> findSeqFromAllConnectedUser(long seq);
}
