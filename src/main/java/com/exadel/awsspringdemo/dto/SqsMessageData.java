package com.exadel.awsspringdemo.dto;

import java.time.LocalDateTime;

public class SqsMessageData {
    private String id;
    private String message;
    private LocalDateTime receivedAt;

    public SqsMessageData(String id, String message, LocalDateTime receivedAt) {
        this.id = id;
        this.message = message;
        this.receivedAt = receivedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }
}