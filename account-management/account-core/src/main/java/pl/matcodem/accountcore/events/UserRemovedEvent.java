package pl.matcodem.accountcore.events;

import lombok.Data;

@Data
public class UserRemovedEvent {
    private String id;
}
