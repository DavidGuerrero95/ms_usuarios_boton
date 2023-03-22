package co.com.udea.kafkaclient.adapter;

import co.com.udea.model.kafka.Email;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IKafkaAdapter {

    void sendMessage(Email email) throws JsonProcessingException;

}
