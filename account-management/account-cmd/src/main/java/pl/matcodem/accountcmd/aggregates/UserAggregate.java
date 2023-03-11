package pl.matcodem.accountcmd.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import pl.matcodem.accountcmd.commands.RegisterUserCommand;
import pl.matcodem.accountcmd.commands.RemoveUserCommand;
import pl.matcodem.accountcmd.commands.UpdateUserCommand;
import pl.matcodem.accountcmd.security.PasswordEncoder;
import pl.matcodem.accountcmd.security.PasswordEncoderImpl;
import pl.matcodem.accountcore.events.UserRegisteredEvent;
import pl.matcodem.accountcore.events.UserRemovedEvent;
import pl.matcodem.accountcore.events.UserUpdatedEvent;
import pl.matcodem.accountcore.models.User;

import static java.util.UUID.*;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;
    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        passwordEncoder = new PasswordEncoderImpl();

        var newUser = command.getUser();
        newUser.setId(command.getId());
        hashPasswordAndSetToUser(newUser);

        var event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    private void hashPasswordAndSetToUser(User user) {
        var password = user.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassword);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        hashPasswordAndSetToUser(updatedUser);

        var event = UserUpdatedEvent.builder()
                .id(randomUUID().toString())
                .user(updatedUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemovedEvent();
        event.setId(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
