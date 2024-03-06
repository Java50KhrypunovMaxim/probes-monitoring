package telran.probes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.*;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.probes.dto.ProbeData;
import telran.probes.service.AvgReducerService;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class AvgReducerControllerTest {


    @Autowired
    InputDestination dataProducer;
    @Autowired
    OutputDestination dataConsumer;
    @MockBean
    AvgReducerService avgReducerService;

    String producerChannelName = "dataProducerChannel"; 
    String consumerChannelName = "dataConsumerChannel";
    static final double AVERAGE_SENSOR_VALUE = 2000;
    static final long SENSOR_ID = 124;
    static final ProbeData NO_AVERAGE_SENSOR_DATA = new ProbeData(100, 50, 0);
    static final ProbeData AVERAGE_SENSOR_DATA = new ProbeData(SENSOR_ID, 110, 0);
    static final ProbeData SENSOR_DATA_WITH_AVERAGE = new ProbeData(SENSOR_ID, AVERAGE_SENSOR_VALUE, 0);

    @Test
    	void testSensorAverage() throws Exception {
            dataProducer.send(new GenericMessage<>(AVERAGE_SENSOR_DATA), producerChannelName);
            Message<byte[]> message = dataConsumer.receive(10, producerChannelName);
            assertNotNull(message);
            ObjectMapper objectMapper = new ObjectMapper();
            ProbeData actual = objectMapper.readValue(message.getPayload(), ProbeData.class);
            assertEquals(SENSOR_DATA_WITH_AVERAGE, actual);
        }
}
