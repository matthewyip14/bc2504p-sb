package com.project.cls.loan_system.lib;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("Pending"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    CANCELLED("Cancelled"),
    OVERDUE("Overdue"),;
    
    private final String description;
    
    PaymentStatus(String description) {
        this.description = description;
    }
} 