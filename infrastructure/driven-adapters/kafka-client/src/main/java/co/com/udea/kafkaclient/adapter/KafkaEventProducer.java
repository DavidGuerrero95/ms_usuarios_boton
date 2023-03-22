package co.com.udea.kafkaclient.adapter;


import co.com.udea.model.kafka.Email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@Slf4j
public class KafkaEventProducer implements IKafkaAdapter{

    @Value("${spring.kafka.template.default-topic}")
    String topicName;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    public KafkaEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Email email) throws JsonProcessingException {
        String key = email.getCode();
        String value = objectMapper.writeValueAsString(email);
        ProducerRecord<String, String> producerRecord = buildProducerRecord(key, value, topicName);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                // TODO Auto-generated method stub
                try {
                    handleSuccess(key, value, result);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                // TODO Auto-generated method stub
                handleFailure(key, value, ex);

            }

        });
    }

    private ProducerRecord<String, String> buildProducerRecord(String key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("send-code-verification", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }

    private void handleFailure(String key, String value, Throwable ex) {
        log.error("Error: send message and the error is {}", ex.getMessage());
        try {

        } catch (Throwable throwable) {
            log.error("Error on OnFailure {}", throwable.getMessage());
        }
    }


    private void handleSuccess(String key, String value, SendResult<String, String> result)
            throws JsonMappingException, JsonProcessingException {
        log.info("Message Sent Successfully for the key :{} and the value is {},partition is {}", key, value,
                result.getRecordMetadata().partition());

    }
}