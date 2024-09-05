package com.example.cryptonotifications.controller;


import com.example.cryptonotifications.dto.ApiResponse;
import com.example.cryptonotifications.model.Notification;
import com.example.cryptonotifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<ApiResponse<Notification>> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = null;
        ApiResponse<Notification> response = null;
        try{
            createdNotification = notificationService.createNotification(notification);
            response = new ApiResponse<>("success", null, createdNotification);
        }catch (Exception e){
            e.printStackTrace();
            response = new ApiResponse<>("failed", e.getLocalizedMessage(), createdNotification);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> listNotifications() {
        List<Notification> notifications = notificationService.listNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotification(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        notificationService.updateNotification(id, notification);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<Void> sendNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotification(id);
        if (notification.isPresent()) {
            notificationService.sendNotificationEmail(notification.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}