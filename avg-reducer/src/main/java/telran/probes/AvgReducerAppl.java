package telran.probes;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.ProbeData;
import telran.probes.service.AvgReducerService;


@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class AvgReducerAppl {
	final StreamBridge streamBridge;
	final AvgReducerService avgReducerService;
	@Value("${app.avg.binding.name}")
	String bindingName;
	
	public static void main(String[] args) {
		SpringApplication.run(AvgReducerAppl.class, args);
	}
	@Bean 
	Consumer<ProbeData> consumerAverageProbes() {
		return this::processProbe;
	}
	
	void processProbe(ProbeData probe) {
		Double averageValue = avgReducerService.getAvgValue(probe);
		Long sensorID = probe.id();
		if (averageValue != null) {
			log.debug("Sensor {}  average value is {}", sensorID, averageValue);
			streamBridge.send(bindingName, new ProbeData(sensorID, averageValue,System.currentTimeMillis()));
		} else {
			log.trace("Sensor {} no average value ", sensorID );
		}
	}
}