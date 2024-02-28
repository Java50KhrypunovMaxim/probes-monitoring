package telran.probes.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.exception.NotFoundException;
import telran.probes.model.SensorEmailsDoc;
import telran.probes.repository.SensorEmailsRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailProviderServiceImp implements EmailProviderService{
	final SensorEmailsRepo sensorEmailsRepo;

	@Override
	public String[] getSensorEmails(long sensorId) {
		SensorEmailsDoc emailsDoc = sensorEmailsRepo.findById(sensorId)
				.orElseThrow(() -> new NotFoundException
						(String.format("sensor %d not found", sensorId)));
		log.debug("sensor {} has been found in DB", sensorId);
		return emailsDoc.getEmails();
	
}
}
