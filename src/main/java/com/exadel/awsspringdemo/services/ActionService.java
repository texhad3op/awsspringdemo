package com.exadel.awsspringdemo.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ActionService {
    @Async
    public String asyn() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        return "oki";
    }
}
