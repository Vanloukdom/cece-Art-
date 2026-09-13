package com.example.brenda.Cece.s.Art.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeUtil {
    public LocalDateTime getDateTime(){
        return LocalDateTime.now();
    }
}
