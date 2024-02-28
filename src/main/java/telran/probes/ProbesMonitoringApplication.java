package telran.probes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "telran.probes")
public class ProbesMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProbesMonitoringApplication.class, args);
	}

}
