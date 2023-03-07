package pl.matcodem.accountquery.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.matcodem.accountquery.dto.UserLookupResponse;
import pl.matcodem.accountquery.queries.FindAllUsersQuery;
import pl.matcodem.accountquery.queries.FindUserByIdQuery;
import pl.matcodem.accountquery.queries.SearchUsersQuery;

import static org.springframework.http.HttpStatus.*;
import static pl.matcodem.accountcore.configuration.EndpointConstants.USERS_ENDPOINT_V1;

@Slf4j
@RestController
@RequestMapping(path = USERS_ENDPOINT_V1)
@RequiredArgsConstructor
public class UserLookupController {

    private final QueryGateway queryGateway;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        try {
            var query = new FindAllUsersQuery();
            var responseType = ResponseTypes.instanceOf(UserLookupResponse.class);
            UserLookupResponse response = queryGateway.query(query, responseType).join();
            return response == null || response.getUsers() == null
                    ? new ResponseEntity<>(response, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Failed to complete get all users request";
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id) {
        try {
            var query = new FindUserByIdQuery(id);
            var responseType = ResponseTypes.instanceOf(UserLookupResponse.class);
            UserLookupResponse response = queryGateway.query(query, responseType).join();
            return response == null || response.getUsers() == null
                    ? new ResponseEntity<>(response, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Failed to complete get user by id request";
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("filter/{filter}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> searchUsersByFilter(@PathVariable("filter") String filter) {
        try {
            var query = new SearchUsersQuery(filter);
            var responseType = ResponseTypes.instanceOf(UserLookupResponse.class);
            UserLookupResponse response = queryGateway.query(query, responseType).join();
            return response == null || response.getUsers() == null
                    ? new ResponseEntity<>(response, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Failed to complete search users by filter request";
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
