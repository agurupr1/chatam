package io.dream.chatam.notifier.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(Map<String, String> userDetails) {
        LOGGER.info("Received message from RabbitMQ: {}", userDetails);

        String email = userDetails.get("userEmailId");
        String userId = userDetails.get("userId");
        String subject = "Welcome to Dream Chatam!";
        String text = "Hello " + email + ",\n\nWelcome to Dream Chatam. Your user ID is " + userId + ".\n\nBest,\nDream Team";

        emailService.sendSimpleMessage(email, subject, text);
    }
}
