package telran.probes.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import telran.probes.model.SensorRangeDoc;

@Repository

public interface SensorRangesRepository extends MongoRepository<SensorRangeDoc, Long>
{

}
