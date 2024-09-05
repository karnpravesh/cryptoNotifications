package com.example.cryptonotifications.repository;

import com.example.cryptonotifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {
}
