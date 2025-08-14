package com.project.cls.loan_system.controller;

import com.project.cls.loan_system.lib.LoanStatus;
import com.project.cls.loan_system.model.LoanDTO;
import com.project.cls.loan_system.service.LoanCalculationService;
import com.project.cls.loan_system.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class LoanController {
    
    private final LoanService loanService;
    private final LoanCalculationService calculationService;
    
    /**
     * Apply for a new loan
     */
    @PostMapping("/apply")
    public ResponseEntity<LoanDTO> applyForLoan(@Valid @RequestBody LoanDTO loanDTO) {
        log.info("Loan application received for user: {}", loanDTO.getUserId());
        
        try {
            LoanDTO createdLoan = loanService.applyForLoan(loanDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
        } catch (Exception e) {
            log.error("Error processing loan application: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Approve a loan
     */
    @PutMapping("/{id}/approve")
    public ResponseEntity<LoanDTO> approveLoan(@PathVariable Long id) {
        log.info("Approving loan: {}", id);
        
        try {
            LoanDTO approvedLoan = loanService.approveLoan(id);
            return ResponseEntity.ok(approvedLoan);
        } catch (Exception e) {
            log.error("Error approving loan: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Reject a loan
     */
    @PutMapping("/{id}/reject")
    public ResponseEntity<LoanDTO> rejectLoan(@PathVariable Long id, @RequestBody Map<String, String> request) {
        log.info("Rejecting loan: {}", id);
        
        String reason = request.getOrDefault("reason", "No reason provided");
        
        try {
            LoanDTO rejectedLoan = loanService.rejectLoan(id, reason);
            return ResponseEntity.ok(rejectedLoan);
        } catch (Exception e) {
            log.error("Error rejecting loan: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Disburse a loan
     */
    @PutMapping("/{id}/disburse")
    public ResponseEntity<LoanDTO> disburseLoan(@PathVariable Long id) {
        log.info("Disbursing loan: {}", id);
        
        try {
            LoanDTO disbursedLoan = loanService.disburseLoan(id);
            return ResponseEntity.ok(disbursedLoan);
        } catch (Exception e) {
            log.error("Error disbursing loan: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get loan by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        log.info("Getting loan by ID: {}", id);
        
        Optional<LoanDTO> loan = loanService.getLoanById(id);
        return loan.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get all loans with pagination
     */
    @GetMapping
    public ResponseEntity<Page<LoanDTO>> getAllLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Getting all loans - page: {}, size: {}", page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<LoanDTO> loans = loanService.getAllLoans(pageable);
        return ResponseEntity.ok(loans);
    }
    
    /**
     * Get loans by user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDTO>> getLoansByUserId(@PathVariable Long userId) {
        log.info("Getting loans for user: {}", userId);
        
        List<LoanDTO> loans = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(loans);
    }
    
    /**
     * Get loans by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanDTO>> getLoansByStatus(@PathVariable LoanStatus status) {
        log.info("Getting loans with status: {}", status);
        
        List<LoanDTO> loans = loanService.getLoansByStatus(status);
        return ResponseEntity.ok(loans);
    }
    
    /**
     * Get active loans for a user
     */
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<LoanDTO>> getActiveLoansByUserId(@PathVariable Long userId) {
        log.info("Getting active loans for user: {}", userId);
        
        List<LoanDTO> loans = loanService.getActiveLoansByUserId(userId);
        return ResponseEntity.ok(loans);
    }
    
    /**
     * Calculate loan payment
     */
    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculateLoan(@RequestBody Map<String, Object> request) {
        log.info("Calculating loan payment");
        
        try {
            BigDecimal loanAmount = new BigDecimal(request.get("loanAmount").toString());
            BigDecimal interestRate = new BigDecimal(request.get("interestRate").toString());
            Integer loanTermMonths = Integer.parseInt(request.get("loanTermMonths").toString());
            
            BigDecimal monthlyPayment = calculationService.calculateMonthlyPayment(loanAmount, interestRate, loanTermMonths);
            BigDecimal totalAmount = calculationService.calculateTotalAmount(monthlyPayment, loanTermMonths);
            BigDecimal totalInterest = calculationService.calculateTotalInterest(totalAmount, loanAmount);
            
            Map<String, Object> result = Map.of(
                "monthlyPayment", monthlyPayment,
                "totalAmount", totalAmount,
                "totalInterest", totalInterest,
                "loanAmount", loanAmount,
                "interestRate", interestRate,
                "loanTermMonths", loanTermMonths
            );
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error calculating loan: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get loan statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getLoanStatistics() {
        log.info("Getting loan statistics");
        
        try {
            List<LoanDTO> pendingLoans = loanService.getLoansByStatus(LoanStatus.PENDING);
            List<LoanDTO> approvedLoans = loanService.getLoansByStatus(LoanStatus.APPROVED);
            List<LoanDTO> activeLoans = loanService.getLoansByStatus(LoanStatus.ACTIVE);
            List<LoanDTO> paidOffLoans = loanService.getLoansByStatus(LoanStatus.PAID_OFF);
            
            Map<String, Object> statistics = Map.of(
                "pendingCount", pendingLoans.size(),
                "approvedCount", approvedLoans.size(),
                "activeCount", activeLoans.size(),
                "paidOffCount", paidOffLoans.size(),
                "totalLoans", pendingLoans.size() + approvedLoans.size() + activeLoans.size() + paidOffLoans.size()
            );
            
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            log.error("Error getting loan statistics: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
} 