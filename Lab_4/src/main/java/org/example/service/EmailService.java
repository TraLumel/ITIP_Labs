package org.example.service;

import org.springframework.stereotype.Service;

@Service("customEmail")
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("EMAIL to " + recipient + ": " + message);
    }
}
