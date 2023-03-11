package pl.matcodem.accountquery.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import pl.matcodem.accountcore.events.UserRegisteredEvent;
import pl.matcodem.accountcore.events.UserRemovedEvent;
import pl.matcodem.accountcore.events.UserUpdatedEvent;
import pl.matcodem.accountquery.repositories.UserRepository;

@Service
@ProcessingGroup("user-group")
@RequiredArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @Override
    @EventHandler
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    @EventHandler
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    @EventHandler
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
