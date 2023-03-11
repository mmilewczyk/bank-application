package pl.matcodem.bankaccquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import pl.matcodem.bankacccore.configuration.AxonConfiguration;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfiguration.class)
@EntityScan(basePackages = "pl.matcodem.bankacccore.models")
@SpringBootApplication
public class BankAccountQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountQueryApplication.class, args);
	}

}
