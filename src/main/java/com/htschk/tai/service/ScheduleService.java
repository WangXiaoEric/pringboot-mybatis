package com.htschk.tai.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleService {

    @Scheduled(cron = "0 0/3 * * * ?")
    public void sampleTrigger() {
        System.out.println("test trigger sample ...");
    }

}
