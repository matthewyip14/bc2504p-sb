package com.project.cls.loan_system.entity;

import com.project.cls.loan_system.lib.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    
    @Column(nullable = false)
    private BigDecimal loanAmount;
    
    @Column(nullable = false)
    private BigDecimal interestRate;
    
    @Column(nullable = false)
    private Integer loanTermMonths;
    
    @Column(nullable = false)
    private BigDecimal monthlyPayment;
    
    @Column(nullable = false)
    private BigDecimal totalAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;
    
    @Column(nullable = false)
    private LocalDateTime applicationDate;
    
    private LocalDateTime approvalDate;
    
    private LocalDateTime disbursementDate;
    
    @Column(length = 1000)
    private String purpose;
    
    @Column(nullable = false)
    private BigDecimal remainingBalance;
    
    @Column(nullable = false)
    private Integer remainingPayments;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 