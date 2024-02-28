package sensorRangeProvider;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.probes.dto.Range;
import telran.probes.model.SensorRangeDoc;
import telran.probes.repository.SensorRangesRepository;
import telran.probes.service.SensorRangeProviderService;
import telran.probes.service.SensorRangeProviderServiceImpl;

@SpringBootTest(classes = { SensorRangesRepository.class, SensorRangeProviderService.class })
@AutoConfigureMockMvc

class SensorRangeProviderTest {
@Autowired
SensorRangeProviderService service;
@Autowired
MockMvc mockMvc;
@Autowired
SensorRangesRepository sensorRangesRepo;

static final long SENSOR_ID = 123;
private static final float MIN_VALUE = 10;
private static final float MAX_VALUE = 100;
SensorRangeDoc sensorRangeDoc = new SensorRangeDoc(SENSOR_ID, MIN_VALUE, MAX_VALUE);
Range sensorRangeExpected = new Range(MIN_VALUE, MAX_VALUE);
@Autowired
ObjectMapper mapper;
static final long SENSOR_NOT_FOUND_ID = 10000;
static final String ERROR_MESSAGE = "sensor " + SENSOR_NOT_FOUND_ID + " not found";
@BeforeEach
void setUp() {
	sensorRangesRepo.save(sensorRangeDoc);
}
	@Test
	void getSensorRange() {
		assertEquals(sensorRangeExpected, service.getSensorRange(SENSOR_ID));
	}

	

}
