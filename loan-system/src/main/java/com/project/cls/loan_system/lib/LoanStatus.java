package com.project.cls.loan_system.lib;

import lombok.Getter;

@Getter
public enum LoanStatus {
    PENDING("Pending Review"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    ACTIVE("Active"),
    PAID_OFF("Paid Off"),
    DEFAULTED("Defaulted"),
    CANCELLED("Cancelled"),;
    
    private final String description;
    
    LoanStatus(String description) {
        this.description = description;
    }
} 