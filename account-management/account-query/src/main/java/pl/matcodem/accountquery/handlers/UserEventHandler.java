package pl.matcodem.accountquery.handlers;

import pl.matcodem.accountcore.events.UserRegisteredEvent;
import pl.matcodem.accountcore.events.UserRemovedEvent;
import pl.matcodem.accountcore.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
