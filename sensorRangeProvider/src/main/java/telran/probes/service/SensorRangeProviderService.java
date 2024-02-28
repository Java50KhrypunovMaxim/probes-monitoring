package telran.probes.service;

import org.springframework.stereotype.Service;

import telran.probes.dto.Range;
import telran.probes.dto.SensorRange;
@Service
public interface SensorRangeProviderService {
	Range getSensorRange(long sensorId);
}