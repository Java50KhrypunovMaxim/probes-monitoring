package telran.probes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.probes.model.SensorEmailsDoc;

public interface SensorEmailsRepo extends MongoRepository<SensorEmailsDoc, Long> {

}
