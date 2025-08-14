package com.project.cls.loan_system.service;

import com.project.cls.loan_system.entity.LoanEntity;
import com.project.cls.loan_system.entity.UserEntity;
import com.project.cls.loan_system.lib.LoanStatus;
import com.project.cls.loan_system.model.LoanDTO;
import com.project.cls.loan_system.repository.LoanRepository;
import com.project.cls.loan_system.repository.UserRepository;
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
public class LoanService {
    
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final LoanCalculationService calculationService;
    
    /**
     * Apply for a new loan
     */
    public LoanDTO applyForLoan(LoanDTO loanDTO) {
        log.info("Processing loan application for user: {}", loanDTO.getUserId());
        
        // Validate user exists
        UserEntity user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + loanDTO.getUserId()));
        
        // Calculate loan details
        BigDecimal monthlyPayment = calculationService.calculateMonthlyPayment(
                loanDTO.getLoanAmount(), 
                loanDTO.getInterestRate(), 
                loanDTO.getLoanTermMonths()
        );
        
        BigDecimal totalAmount = calculationService.calculateTotalAmount(monthlyPayment, loanDTO.getLoanTermMonths());
        
        // Create loan entity
        LoanEntity loan = LoanEntity.builder()
                .user(user)
                .loanAmount(loanDTO.getLoanAmount())
                .interestRate(loanDTO.getInterestRate())
                .loanTermMonths(loanDTO.getLoanTermMonths())
                .monthlyPayment(monthlyPayment)
                .totalAmount(totalAmount)
                .status(LoanStatus.PENDING)
                .applicationDate(LocalDateTime.now())
                .purpose(loanDTO.getPurpose())
                .remainingBalance(loanDTO.getLoanAmount())
                .remainingPayments(loanDTO.getLoanTermMonths())
                .build();
        
        LoanEntity savedLoan = loanRepository.save(loan);
        
        return convertToDTO(savedLoan);
    }
    
    /**
     * Approve a loan application
     */
    public LoanDTO approveLoan(Long loanId) {
        log.info("Approving loan: {}", loanId);
        
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new RuntimeException("Loan is not in pending status");
        }
        
        loan.setStatus(LoanStatus.APPROVED);
        loan.setApprovalDate(LocalDateTime.now());
        
        LoanEntity savedLoan = loanRepository.save(loan);
        
        return convertToDTO(savedLoan);
    }
    
    /**
     * Reject a loan application
     */
    public LoanDTO rejectLoan(Long loanId, String reason) {
        log.info("Rejecting loan: {} with reason: {}", loanId, reason);
        
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new RuntimeException("Loan is not in pending status");
        }
        
        loan.setStatus(LoanStatus.REJECTED);
        
        LoanEntity savedLoan = loanRepository.save(loan);
        
        return convertToDTO(savedLoan);
    }
    
    /**
     * Disburse an approved loan
     */
    public LoanDTO disburseLoan(Long loanId) {
        log.info("Disbursing loan: {}", loanId);
        
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
        
        if (loan.getStatus() != LoanStatus.APPROVED) {
            throw new RuntimeException("Loan is not approved");
        }
        
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setDisbursementDate(LocalDateTime.now());
        
        LoanEntity savedLoan = loanRepository.save(loan);
        
        return convertToDTO(savedLoan);
    }
    
    /**
     * Get loan by ID
     */
    @Transactional(readOnly = true)
    public Optional<LoanDTO> getLoanById(Long loanId) {
        return loanRepository.findById(loanId).map(this::convertToDTO);
    }
    
    /**
     * Get all loans for a user
     */
    @Transactional(readOnly = true)
    public List<LoanDTO> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    /**
     * Get loans by status
     */
    @Transactional(readOnly = true)
    public List<LoanDTO> getLoansByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    /**
     * Get all loans with pagination
     */
    @Transactional(readOnly = true)
    public Page<LoanDTO> getAllLoans(Pageable pageable) {
        return loanRepository.findAll(pageable).map(this::convertToDTO);
    }
    
    /**
     * Get active loans for a user
     */
    @Transactional(readOnly = true)
    public List<LoanDTO> getActiveLoansByUserId(Long userId) {
        return loanRepository.findActiveLoansByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    /**
     * Update loan remaining balance and payments after a payment
     */
    public void updateLoanAfterPayment(Long loanId, BigDecimal paymentAmount) {
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
        
        BigDecimal newRemainingBalance = loan.getRemainingBalance().subtract(paymentAmount);
        if (newRemainingBalance.compareTo(BigDecimal.ZERO) < 0) {
            newRemainingBalance = BigDecimal.ZERO;
        }
        
        loan.setRemainingBalance(newRemainingBalance);
        loan.setRemainingPayments(loan.getRemainingPayments() - 1);
        
        // Check if loan is paid off
        if (newRemainingBalance.compareTo(BigDecimal.ZERO) == 0) {
            loan.setStatus(LoanStatus.PAID_OFF);
        }
        
        loanRepository.save(loan);
    }
    
    /**
     * Convert entity to DTO
     */
    private LoanDTO convertToDTO(LoanEntity loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setUserId(loan.getUser().getId());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setLoanTermMonths(loan.getLoanTermMonths());
        dto.setMonthlyPayment(loan.getMonthlyPayment());
        dto.setTotalAmount(loan.getTotalAmount());
        dto.setStatus(loan.getStatus());
        dto.setApplicationDate(loan.getApplicationDate());
        dto.setApprovalDate(loan.getApprovalDate());
        dto.setDisbursementDate(loan.getDisbursementDate());
        dto.setPurpose(loan.getPurpose());
        dto.setRemainingBalance(loan.getRemainingBalance());
        dto.setRemainingPayments(loan.getRemainingPayments());
        dto.setCreatedAt(loan.getCreatedAt());
        dto.setUpdatedAt(loan.getUpdatedAt());
        dto.setUserFullName(loan.getUser().getName());
        dto.setUserEmail(loan.getUser().getEmail());
        
        return dto;
    }
} 