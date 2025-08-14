package com.project.cls.loan_system.service;

import com.project.cls.loan_system.entity.LoanEntity;
import com.project.cls.loan_system.entity.PaymentEntity;
import com.project.cls.loan_system.lib.PaymentStatus;
import com.project.cls.loan_system.model.PaymentDTO;
import com.project.cls.loan_system.repository.LoanRepository;
import com.project.cls.loan_system.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;
    private final LoanService loanService;
    private final LoanCalculationService calculationService;
    
    /**
     * Process a payment for a loan
     */
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        log.info("Processing payment for loan: {}", paymentDTO.getLoanId());
        
        // Validate loan exists and is active
        LoanEntity loan = loanRepository.findById(paymentDTO.getLoanId())
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + paymentDTO.getLoanId()));
        
        if (loan.getStatus().name().equals("ACTIVE") == false) {
            throw new RuntimeException("Loan is not active for payments");
        }
        
        // Get next payment number
        Integer nextPaymentNumber = getNextPaymentNumber(paymentDTO.getLoanId());
        
        // Calculate payment breakdown
        PaymentBreakdown breakdown = calculatePaymentBreakdown(loan, paymentDTO.getAmount());
        
        // Create payment entity
        PaymentEntity payment = PaymentEntity.builder()
                .loan(loan)
                .amount(paymentDTO.getAmount())
                .paymentDate(paymentDTO.getPaymentDate() != null ? paymentDTO.getPaymentDate() : LocalDateTime.now())
                .status(PaymentStatus.COMPLETED)
                .paymentNumber(nextPaymentNumber)
                .principalAmount(breakdown.getPrincipalAmount())
                .interestAmount(breakdown.getInterestAmount())
                .remainingBalance(breakdown.getRemainingBalance())
                .notes(paymentDTO.getNotes())
                .build();
        
        PaymentEntity savedPayment = paymentRepository.save(payment);
        
        // Update loan balance and payments
        loanService.updateLoanAfterPayment(loan.getId(), breakdown.getPrincipalAmount());
        
        return convertToDTO(savedPayment);
    }
    
    /**
     * Get payment by ID
     */
    @Transactional(readOnly = true)
    public Optional<PaymentDTO> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(this::convertToDTO);
    }
    
    /**
     * Get all payments for a loan
     */
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByLoanId(Long loanId) {
        return paymentRepository.findByLoanId(loanId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    /**
     * Get payments by status
     */
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    /**
     * Get all payments with pagination
     */
    @Transactional(readOnly = true)
    public Page<PaymentDTO> getAllPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable).map(this::convertToDTO);
    }
    
    /**
     * Get payments for a user
     */
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    /**
     * Get payment schedule for a loan
     */
    @Transactional(readOnly = true)
    public List<LoanCalculationService.PaymentScheduleEntry> getPaymentSchedule(Long loanId) {
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
        
        return calculationService.generatePaymentSchedule(
                loan.getLoanAmount(),
                loan.getMonthlyPayment(),
                loan.getInterestRate(),
                loan.getLoanTermMonths()
        );
    }
    
    /**
     * Get next payment number for a loan
     */
    private Integer getNextPaymentNumber(Long loanId) {
        PaymentEntity lastPayment = paymentRepository.findLastPaymentByLoanId(loanId);
        return lastPayment != null ? lastPayment.getPaymentNumber() + 1 : 1;
    }
    
    /**
     * Calculate payment breakdown (principal vs interest)
     */
    private PaymentBreakdown calculatePaymentBreakdown(LoanEntity loan, BigDecimal paymentAmount) {
        BigDecimal remainingBalance = loan.getRemainingBalance();
        BigDecimal monthlyInterestRate = loan.getInterestRate().divide(BigDecimal.valueOf(1200), 8, BigDecimal.ROUND_HALF_UP);
        
        // Calculate interest for this payment
        BigDecimal interestAmount = remainingBalance.multiply(monthlyInterestRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        
        // Calculate principal for this payment
        BigDecimal principalAmount = paymentAmount.subtract(interestAmount);
        
        // Ensure principal doesn't exceed remaining balance
        if (principalAmount.compareTo(remainingBalance) > 0) {
            principalAmount = remainingBalance;
        }
        
        // Calculate new remaining balance
        BigDecimal newRemainingBalance = remainingBalance.subtract(principalAmount);
        if (newRemainingBalance.compareTo(BigDecimal.ZERO) < 0) {
            newRemainingBalance = BigDecimal.ZERO;
        }
        
        return new PaymentBreakdown(principalAmount, interestAmount, newRemainingBalance);
    }
    
    /**
     * Convert entity to DTO
     */
    private PaymentDTO convertToDTO(PaymentEntity payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setLoanId(payment.getLoan().getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setStatus(payment.getStatus());
        dto.setPaymentNumber(payment.getPaymentNumber());
        dto.setPrincipalAmount(payment.getPrincipalAmount());
        dto.setInterestAmount(payment.getInterestAmount());
        dto.setRemainingBalance(payment.getRemainingBalance());
        dto.setNotes(payment.getNotes());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
        dto.setLoanReference("LOAN-" + payment.getLoan().getId());
        dto.setUserFullName(payment.getLoan().getUser().getName());
        
        return dto;
    }
    
    /**
     * Inner class for payment breakdown
     */
    private static class PaymentBreakdown {
        private final BigDecimal principalAmount;
        private final BigDecimal interestAmount;
        private final BigDecimal remainingBalance;
        
        public PaymentBreakdown(BigDecimal principalAmount, BigDecimal interestAmount, BigDecimal remainingBalance) {
            this.principalAmount = principalAmount;
            this.interestAmount = interestAmount;
            this.remainingBalance = remainingBalance;
        }
        
        public BigDecimal getPrincipalAmount() { return principalAmount; }
        public BigDecimal getInterestAmount() { return interestAmount; }
        public BigDecimal getRemainingBalance() { return remainingBalance; }
    }
    
} 