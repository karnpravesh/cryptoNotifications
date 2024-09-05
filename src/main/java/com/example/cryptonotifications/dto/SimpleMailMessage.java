package com.example.cryptonotifications.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SimpleMailMessage {

    private String to;
    private String subject;
    private String text;
}
