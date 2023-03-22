package co.com.udea.kafkaclient.adapter;

import co.com.udea.kafkaclient.adapter.helper.AdapterOperations;
import co.com.udea.model.kafka.Email;
import co.com.udea.model.kafka.gateways.KafkaPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaEventAdapter
        extends AdapterOperations<IKafkaAdapter>
        implements KafkaPort {

    public KafkaEventAdapter(IKafkaAdapter repository) {
        super(repository);
    }

    @Override
    public void sendMessage(Email email) throws JsonProcessingException {
        repository.sendMessage(email);
    }
}
