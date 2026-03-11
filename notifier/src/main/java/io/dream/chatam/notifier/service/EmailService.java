package io.dream.chatam.notifier.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        LOGGER.info("Sending email to: {}", to);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@dream.io");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            LOGGER.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            LOGGER.error("Failed to send email to: {}", to, e);
        }
    }
}