package com.rpa.TestLinkService.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestLinkCheckExecutor {

    @Scheduled(cron = "0 */1 * * * *")
    public void execute(){
        log.info("Listening to kafka consumer");

    }
}
