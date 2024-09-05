package com.example.cryptonotifications.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse <T>{
    private String status;
    private String error;
    private T data;
}
