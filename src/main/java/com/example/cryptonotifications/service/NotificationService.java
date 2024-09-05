package com.example.cryptonotifications.service;

import com.example.cryptonotifications.dto.SimpleMailMessage;
import com.example.cryptonotifications.model.Notification;
import com.example.cryptonotifications.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepository;


    public Notification createNotification(Notification notification) {
        notification.setStatus(Notification.NotificationStatus.PENDING);
        return notificationRepository.save(notification);
    }

    public List<Notification> listNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotification(Long id) {
        return notificationRepository.findById(id);
    }

    public void updateNotification(Long id, Notification updatedNotification) {
        if (notificationRepository.existsById(id)) {
            updatedNotification.setId(id);
            notificationRepository.save(updatedNotification);
        }
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public void sendNotificationEmail(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("karnpravesh@yahoo.com");
        message.setSubject("Crypto Notification: " + notification.getName());
        message.setText("Price: " + notification.getCurrentPrice() +
                "\nDaily Change: " + notification.getDailyPercChange() +
                "\nVolume: " + notification.getTradingVolume());
        try {
            System.out.println("Notification SENT: "+message.toString());
            notification.setStatus(Notification.NotificationStatus.SENT);
        } catch (Exception e) {
            notification.setStatus(Notification.NotificationStatus.FAILED);
        }
        notificationRepository.save(notification);
    }
}
