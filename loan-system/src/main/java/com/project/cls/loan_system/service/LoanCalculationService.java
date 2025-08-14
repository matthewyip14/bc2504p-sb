package com.project.cls.loan_system.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanCalculationService {
    
    /**
     * Calculate monthly payment using the standard loan payment formula
     * P = L[c(1 + c)^n]/[(1 + c)^n - 1]
     * Where: P = monthly payment, L = loan amount, c = monthly interest rate, n = total number of payments
     */
    public BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal annualInterestRate, int loanTermMonths) {
        if (loanAmount.compareTo(BigDecimal.ZERO) <= 0 || annualInterestRate.compareTo(BigDecimal.ZERO) <= 0 || loanTermMonths <= 0) {
            throw new IllegalArgumentException("Invalid loan parameters");
        }
        
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), 8, RoundingMode.HALF_UP);
        BigDecimal monthlyInterestRateDecimal = monthlyInterestRate.divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP);
        
        // Calculate (1 + c)^n
        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyInterestRateDecimal);
        BigDecimal powerTerm = onePlusRate.pow(loanTermMonths);
        
        // Calculate numerator: L * c * (1 + c)^n
        BigDecimal numerator = loanAmount.multiply(monthlyInterestRateDecimal).multiply(powerTerm);
        
        // Calculate denominator: (1 + c)^n - 1
        BigDecimal denominator = powerTerm.subtract(BigDecimal.ONE);
        
        // Calculate monthly payment
        BigDecimal monthlyPayment = numerator.divide(denominator, 2, RoundingMode.HALF_UP);
        
        return monthlyPayment;
    }
    
    /**
     * Calculate total amount to be paid over the loan term
     */
    public BigDecimal calculateTotalAmount(BigDecimal monthlyPayment, int loanTermMonths) {
        return monthlyPayment.multiply(BigDecimal.valueOf(loanTermMonths));
    }
    
    /**
     * Calculate total interest to be paid
     */
    public BigDecimal calculateTotalInterest(BigDecimal totalAmount, BigDecimal loanAmount) {
        return totalAmount.subtract(loanAmount);
    }
    
    /**
     * Generate payment schedule for the loan
     */
    public List<PaymentScheduleEntry> generatePaymentSchedule(BigDecimal loanAmount, BigDecimal monthlyPayment, 
                                                             BigDecimal annualInterestRate, int loanTermMonths) {
        List<PaymentScheduleEntry> schedule = new ArrayList<>();
        BigDecimal remainingBalance = loanAmount;
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(1200), 8, RoundingMode.HALF_UP);
        
        for (int paymentNumber = 1; paymentNumber <= loanTermMonths; paymentNumber++) {
            // Calculate interest for this payment
            BigDecimal interestAmount = remainingBalance.multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
            
            // Calculate principal for this payment
            BigDecimal principalAmount = monthlyPayment.subtract(interestAmount);
            
            // Update remaining balance
            remainingBalance = remainingBalance.subtract(principalAmount);
            
            // Ensure remaining balance doesn't go negative due to rounding
            if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
                remainingBalance = BigDecimal.ZERO;
            }
            
            PaymentScheduleEntry entry = PaymentScheduleEntry.builder()
                    .paymentNumber(paymentNumber)
                    .paymentAmount(monthlyPayment)
                    .principalAmount(principalAmount)
                    .interestAmount(interestAmount)
                    .remainingBalance(remainingBalance)
                    .build();
            
            schedule.add(entry);
        }
        
        return schedule;
    }
    
    /**
     * Calculate remaining balance after a certain number of payments
     */
    public BigDecimal calculateRemainingBalance(BigDecimal loanAmount, BigDecimal monthlyPayment, 
                                               BigDecimal annualInterestRate, int paymentsMade) {
        if (paymentsMade <= 0) {
            return loanAmount;
        }
        
        BigDecimal remainingBalance = loanAmount;
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(1200), 8, RoundingMode.HALF_UP);
        
        for (int i = 1; i <= paymentsMade; i++) {
            BigDecimal interestAmount = remainingBalance.multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal principalAmount = monthlyPayment.subtract(interestAmount);
            remainingBalance = remainingBalance.subtract(principalAmount);
            
            if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
                remainingBalance = BigDecimal.ZERO;
                break;
            }
        }
        
        return remainingBalance;
    }
    
    /**
     * Inner class to represent a payment schedule entry
     */
    public static class PaymentScheduleEntry {
        private int paymentNumber;
        private BigDecimal paymentAmount;
        private BigDecimal principalAmount;
        private BigDecimal interestAmount;
        private BigDecimal remainingBalance;
        
        // Builder pattern
        public static PaymentScheduleEntryBuilder builder() {
            return new PaymentScheduleEntryBuilder();
        }
        
        public static class PaymentScheduleEntryBuilder {
            private PaymentScheduleEntry entry = new PaymentScheduleEntry();
            
            public PaymentScheduleEntryBuilder paymentNumber(int paymentNumber) {
                entry.paymentNumber = paymentNumber;
                return this;
            }
            
            public PaymentScheduleEntryBuilder paymentAmount(BigDecimal paymentAmount) {
                entry.paymentAmount = paymentAmount;
                return this;
            }
            
            public PaymentScheduleEntryBuilder principalAmount(BigDecimal principalAmount) {
                entry.principalAmount = principalAmount;
                return this;
            }
            
            public PaymentScheduleEntryBuilder interestAmount(BigDecimal interestAmount) {
                entry.interestAmount = interestAmount;
                return this;
            }
            
            public PaymentScheduleEntryBuilder remainingBalance(BigDecimal remainingBalance) {
                entry.remainingBalance = remainingBalance;
                return this;
            }
            
            public PaymentScheduleEntry build() {
                return entry;
            }
        }
        
        // Getters
        public int getPaymentNumber() { return paymentNumber; }
        public BigDecimal getPaymentAmount() { return paymentAmount; }
        public BigDecimal getPrincipalAmount() { return principalAmount; }
        public BigDecimal getInterestAmount() { return interestAmount; }
        public BigDecimal getRemainingBalance() { return remainingBalance; }
    }
} 