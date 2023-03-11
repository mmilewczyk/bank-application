package pl.matcodem.bankacccmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import pl.matcodem.bankacccore.configuration.AxonConfiguration;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfiguration.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BankAccountCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountCommandApplication.class, args);
	}

}
