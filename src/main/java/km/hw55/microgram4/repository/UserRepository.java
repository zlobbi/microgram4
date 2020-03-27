package km.hw55.microgram4.repository;

import km.hw55.microgram4.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByUsername(String login);

    User findByEmail(String email);

    User existsByEmail(String email);

    boolean existsByUsernameAndEmail(String username, String email);
}
// add searching user by name
// verify user existence by email
// get comments for publication
// get user subscriptions count
// get user subscribers count
