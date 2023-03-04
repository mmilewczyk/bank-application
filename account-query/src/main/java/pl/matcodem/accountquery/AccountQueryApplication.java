package pl.matcodem.accountquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.matcodem.accountcore.configuration.AxonConfiguration;

@SpringBootApplication
@Import({AxonConfiguration.class})
public class AccountQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }

}
