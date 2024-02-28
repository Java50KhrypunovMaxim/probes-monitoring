package telran.probes;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.DeviationData;
import telran.probes.dto.ProbeData;
import telran.probes.dto.Range;
import telran.probes.service.RangeProviderClientService;

@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan(basePackages = "telran.probes.service")
@Slf4j
public class AnalyzerAppl {
	String producerBindingName = "analyzerProducer-out-0";
	final RangeProviderClientService clientService;
	final StreamBridge streamBridge;
	public static void main(String[] args) {
		SpringApplication.run(AnalyzerAppl.class, args);
	}
	
	@Bean
	Consumer<ProbeData> analyzerConsumer() {
		return probeData -> probeDataAnalyzing(probeData);
	}
	
	
	private void probeDataAnalyzing(ProbeData probeData) {
		log.trace("received probe: {}", probeData);
		Range range = clientService.getRange(probeData.id());
		double probeValue = probeData.value();
		double minValue = range.minValue();
		double maxValue = range.maxValue();

		if (probeValue < minValue || probeValue > maxValue) {
		    double deviationValue = probeValue < minValue ? probeValue - minValue : probeValue - maxValue;
		    DeviationData deviation = new DeviationData(probeData.id(), deviationValue, probeValue, System.currentTimeMillis());
		    streamBridge.send(producerBindingName, deviation);
		    log.debug("deviation data {} sent to {}", deviation, producerBindingName);
		}

}}