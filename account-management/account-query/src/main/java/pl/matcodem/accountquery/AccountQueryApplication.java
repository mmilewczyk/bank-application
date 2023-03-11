package pl.matcodem.accountquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import pl.matcodem.accountcore.configuration.AxonConfiguration;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({AxonConfiguration.class})
public class AccountQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }

}
