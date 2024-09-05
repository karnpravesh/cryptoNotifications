package com.example.cryptonotifications.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double currentPrice;
    private Double dailyPercChange;
    private Double tradingVolume;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    public enum NotificationStatus {
        SENT,
        PENDING,
        FAILED
    }
}
