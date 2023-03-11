package pl.matcodem.accountcore.events;

import lombok.Builder;
import lombok.Data;
import pl.matcodem.accountcore.models.User;

@Data
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
