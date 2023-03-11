package pl.matcodem.accountquery.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import pl.matcodem.accountcore.models.User;
import pl.matcodem.accountquery.dto.UserLookupResponse;
import pl.matcodem.accountquery.queries.FindAllUsersQuery;
import pl.matcodem.accountquery.queries.FindUserByIdQuery;
import pl.matcodem.accountquery.queries.SearchUsersQuery;
import pl.matcodem.accountquery.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.*;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @Override
    @QueryHandler
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        List<User> users = userRepository.findAll();
        return new UserLookupResponse(users);
    }

    @Override
    @QueryHandler
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        Optional<User> user = userRepository.findById(query.getId());
        return user.map(value -> new UserLookupResponse(singletonList(value))).orElseGet(null);
    }

    @Override
    @QueryHandler
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        List<User> users = userRepository.findByFilterRegex(query.getFilter());
        return new UserLookupResponse(users);
    }
}
