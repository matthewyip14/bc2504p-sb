package com.project.cls.loan_system.model;

import com.project.cls.loan_system.lib.LoanStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanDTO {
    
    private Long id;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "100.0", message = "Loan amount must be at least $100")
    @DecimalMax(value = "1000000.0", message = "Loan amount cannot exceed $1,000,000")
    private BigDecimal loanAmount;
    
    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.01", message = "Interest rate must be at least 1%")
    @DecimalMax(value = "0.30", message = "Interest rate cannot exceed 30%")
    private BigDecimal interestRate;
    
    @NotNull(message = "Loan term is required")
    @Min(value = 1, message = "Loan term must be at least 1 month")
    @Max(value = 360, message = "Loan term cannot exceed 360 months (30 years)")
    private Integer loanTermMonths;
    
    private BigDecimal monthlyPayment;
    private BigDecimal totalAmount;
    private LoanStatus status;
    private LocalDateTime applicationDate;
    private LocalDateTime approvalDate;
    private LocalDateTime disbursementDate;
    
    @NotBlank(message = "Loan purpose is required")
    @Size(max = 1000, message = "Purpose description cannot exceed 1000 characters")
    private String purpose;
    
    private BigDecimal remainingBalance;
    private Integer remainingPayments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields for loan application
    private String userFullName;
    private String userEmail;
} 