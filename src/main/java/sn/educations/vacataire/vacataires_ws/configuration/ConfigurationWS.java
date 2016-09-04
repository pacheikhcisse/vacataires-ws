package sn.educations.vacataire.vacataires_ws.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import sn.educations.vacataire.dao.configuration.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = { "sn.educations.vacataire.vacataires_ws"})
@Import({Configuration.class, WebConfig.class})
public class ConfigurationWS {
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationWS.class, args);
	}
}
