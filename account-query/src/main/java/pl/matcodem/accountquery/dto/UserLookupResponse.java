package pl.matcodem.accountquery.dto;

import pl.matcodem.accountcore.dto.BaseResponse;
import pl.matcodem.accountcore.models.User;

import java.util.List;

public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(String message) {
        super(message);
    }

    public UserLookupResponse(String message, List<User> users) {
        super(message);
        this.users = users;
    }

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
