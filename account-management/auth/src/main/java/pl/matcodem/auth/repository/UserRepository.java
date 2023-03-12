package pl.matcodem.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.matcodem.accountcore.models.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByAccount_Username(String username);
}
