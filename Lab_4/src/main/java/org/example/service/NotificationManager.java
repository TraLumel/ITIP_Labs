package org.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private final MessageService primaryService;
    private final MessageService customEmailService;
    private final List<MessageService> allServices;

    @Autowired
    public NotificationManager(
            MessageService primaryService, // Сюда внедрится @Primary SmsService
            @Qualifier("customEmail") MessageService customEmailService, // Сюда внедрится EmailService
            List<MessageService> allServices // Сюда попадут все сервисы
    ) {
        this.primaryService = primaryService;
        this.customEmailService = customEmailService;
        this.allServices = allServices;
    }

    public void notify(String message, String recipient) {
        System.out.println("--- Вызов Primary Service ---");
        primaryService.sendMessage(message, recipient);

        System.out.println("--- Вызов Custom Email Service ---");
        customEmailService.sendMessage(message, recipient);

        System.out.println("--- Вызов всех сервисов через List ---");
        allServices.forEach(service -> service.sendMessage(message, recipient));
    }
}
