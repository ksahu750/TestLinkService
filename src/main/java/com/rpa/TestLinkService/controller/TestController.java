package com.rpa.TestLinkService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpa.TestLinkService.dto.KafkaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RequestMapping(value = "/CoviamTechnology")
@RestController
public class TestController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = "/HiringTest")
    public ResponseEntity<KafkaResponse> testResult(@RequestParam String email){
        Random random=new Random();
        int x=random.nextInt(2);
        Boolean result;
        if(0 == x) {
            result = false;
        }else {
            result = true;
        }
        KafkaResponse kafkaResponse = new KafkaResponse();
        kafkaResponse.setEmail(email);
        kafkaResponse.setResult(result.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(kafkaResponse);
            kafkaTemplate.send("rpa.testLinkClicked", payload);
            log.info("sending Payload: {}", payload);

        } catch (JsonProcessingException e){
            log.info("Could not map Object: {} to String due to exception: {}", kafkaResponse, e);
        }
        return new ResponseEntity<>(kafkaResponse,HttpStatus.OK);
    }
}
