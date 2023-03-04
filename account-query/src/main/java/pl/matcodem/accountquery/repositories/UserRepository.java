package pl.matcodem.accountquery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.matcodem.accountcore.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
