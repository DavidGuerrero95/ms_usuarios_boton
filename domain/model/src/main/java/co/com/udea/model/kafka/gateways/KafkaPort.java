package co.com.udea.model.kafka.gateways;

import co.com.udea.model.kafka.Email;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaPort {


    void sendMessage(Email email) throws JsonProcessingException;
}
