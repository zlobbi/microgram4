package km.hw55.microgram4.repository;

import km.hw55.microgram4.model.Subscribtion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscribtionRepository extends CrudRepository<Subscribtion, String> {
    List<Subscribtion> findAllBySubscriber_Id(String id);
}
// get subscriber
// get subscribiant

