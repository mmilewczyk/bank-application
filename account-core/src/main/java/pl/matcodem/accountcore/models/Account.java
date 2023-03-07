package pl.matcodem.accountcore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Size(min = 2, message = "username must contanins at least 2 characters")
    private String username;
    @Size(min = 8, message = "password must contanins at least 8 characters")
    private String password;
    @NotNull(message = "specify at least 1 user role")
    private List<Role> roles;
}
