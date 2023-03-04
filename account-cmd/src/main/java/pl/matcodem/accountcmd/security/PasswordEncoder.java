package pl.matcodem.accountcmd.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
