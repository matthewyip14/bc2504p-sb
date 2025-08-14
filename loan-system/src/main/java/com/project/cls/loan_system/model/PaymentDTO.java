package com.project.cls.loan_system.model;

import com.project.cls.loan_system.lib.PaymentStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
    
    private Long id;
    
    @NotNull(message = "Loan ID is required")
    private Long loanId;
    
    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.01", message = "Payment amount must be greater than $0")
    private BigDecimal amount;
    
    @NotNull(message = "Payment date is required")
    private LocalDateTime paymentDate;
    
    private PaymentStatus status;
    private Integer paymentNumber;
    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private BigDecimal remainingBalance;
    
    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields for display
    private String loanReference;
    private String userFullName;
} 