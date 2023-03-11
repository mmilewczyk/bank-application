package pl.matcodem.accountquery.handlers;

import pl.matcodem.accountquery.dto.UserLookupResponse;
import pl.matcodem.accountquery.queries.FindAllUsersQuery;
import pl.matcodem.accountquery.queries.FindUserByIdQuery;
import pl.matcodem.accountquery.queries.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookupResponse getAllUsers(FindAllUsersQuery query);
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
}
