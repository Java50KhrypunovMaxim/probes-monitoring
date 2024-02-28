package telran.probes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.Range;
import telran.probes.service.SensorRangeProviderService;

@RestController
@RequestMapping("/api/v1/sensorrangesprovider")
@RequiredArgsConstructor
@Slf4j
public class SensorRangesProviderController {
	final SensorRangeProviderService service;
	@GetMapping("/{id}")
	Range getSensorRange(@PathVariable(name="id") long id) {
		Range sensorRange =  service.getSensorRange(id);
		log.debug("sensor range received is {}", sensorRange);
		return sensorRange;
	}
}
