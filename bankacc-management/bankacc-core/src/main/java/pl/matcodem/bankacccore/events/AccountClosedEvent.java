package pl.matcodem.bankacccore.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountClosedEvent {

    private String id;
}
