package pl.matcodem.accountcmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.matcodem.accountcore.configuration.AxonConfiguration;

@SpringBootApplication
@Import({AxonConfiguration.class})
public class AccountCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountCommandApplication.class, args);
    }

}
