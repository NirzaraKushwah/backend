package com.restaurant.service;

import com.restaurant.entity.Item;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLowStockAlert(String email, Item item) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Low Stock Alert");

        message.setText(
                "Item: " + item.getName() +
                        "\nQuantity: " + item.getQuantity() +
                        "\nThreshold: " + item.getThreshold()
        );

        mailSender.send(message);
    }
}