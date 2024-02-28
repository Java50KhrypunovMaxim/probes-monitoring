package sensorEmailsProvider;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.probes.model.SensorEmailsDoc;
import telran.probes.repository.SensorEmailsRepo;
import telran.probes.service.EmailProviderService;

@SpringBootTest
@AutoConfigureMockMvc
class EmailDataTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	SensorEmailsRepo sensorEmailsRepo;
	@Autowired
	EmailProviderService emailProviderService;
	
	String url;
	static final long SENSOR_ID = 123;

	private static final String EMAIL1 = "service123@gmail";
	SensorEmailsDoc sensorEmailsDoc = new SensorEmailsDoc(SENSOR_ID, new String[]{EMAIL1});
	String[]emailsExpected = {EMAIL1};
	@Autowired
	ObjectMapper mapper;
	@BeforeEach
	void setUp() {
		sensorEmailsRepo.save(sensorEmailsDoc);
	}
	
	void getSensorEmails ()
	{
		assertEquals(emailsExpected, emailProviderService.getSensorEmails(SENSOR_ID));
	}
}
