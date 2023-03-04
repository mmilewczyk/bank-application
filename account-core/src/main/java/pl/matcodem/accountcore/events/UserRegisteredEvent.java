package pl.matcodem.accountcore.events;

import lombok.Builder;
import lombok.Data;
import pl.matcodem.accountcore.models.User;

@Data
@Builder
public class UserRegisteredEvent {
    private String id;
    private User user;
}
