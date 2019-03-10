package com.rpa.TestLinkService.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.rpa.TestLinkService.dto.KafkaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @KafkaListener(topics = "rpa.testLinkClicked",
            containerFactory = "kafkaListenerContainerFactory")
    public void recieveAudit(String payLoad) {
        log.info("Payload Recieved, {}" + payLoad);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader eventReader = objectMapper.readerFor(KafkaResponse.class);
        try {
            KafkaResponse kafkaResponse = eventReader.readValue(payLoad);
            pushToKafka(kafkaResponse);

        } catch (IOException e) {
            log.info("Could not map String: {} to Object due to exception: {}", payLoad, e);
        }

    }

    public void pushToKafka(KafkaResponse kafkaResponse){
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(kafkaResponse);
            kafkaTemplate.send("rpa.testLinkClickedFO", payload);
            log.info("sending Payload: {}", payload);

        } catch (JsonProcessingException e){
            log.info("Could not map Object: {} to String due to exception: {}", kafkaResponse, e);
        }
    }


}
