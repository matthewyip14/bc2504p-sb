package com.project.cls.loan_system.controller;

import com.project.cls.loan_system.lib.PaymentStatus;
import com.project.cls.loan_system.model.PaymentDTO;
import com.project.cls.loan_system.service.LoanCalculationService;
import com.project.cls.loan_system.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    /**
     * Process a payment
     */
    @PostMapping("/process")
    public ResponseEntity<PaymentDTO> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        log.info("Processing payment for loan: {}", paymentDTO.getLoanId());
        
        try {
            PaymentDTO processedPayment = paymentService.processPayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(processedPayment);
        } catch (Exception e) {
            log.error("Error processing payment: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get payment by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        log.info("Getting payment by ID: {}", id);
        
        Optional<PaymentDTO> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get all payments with pagination
     */
    @GetMapping
    public ResponseEntity<Page<PaymentDTO>> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Getting all payments - page: {}, size: {}", page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PaymentDTO> payments = paymentService.getAllPayments(pageable);
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Get payments by loan ID
     */
    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByLoanId(@PathVariable Long loanId) {
        log.info("Getting payments for loan: {}", loanId);
        
        List<PaymentDTO> payments = paymentService.getPaymentsByLoanId(loanId);
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Get payments by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        log.info("Getting payments with status: {}", status);
        
        List<PaymentDTO> payments = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Get payments by user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserId(@PathVariable Long userId) {
        log.info("Getting payments for user: {}", userId);
        
        List<PaymentDTO> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Get payment schedule for a loan
     */
    @GetMapping("/schedule/{loanId}")
    public ResponseEntity<List<LoanCalculationService.PaymentScheduleEntry>> getPaymentSchedule(@PathVariable Long loanId) {
        log.info("Getting payment schedule for loan: {}", loanId);
        
        try {
            List<LoanCalculationService.PaymentScheduleEntry> schedule = paymentService.getPaymentSchedule(loanId);
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            log.error("Error getting payment schedule: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get payment statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getPaymentStatistics() {
        log.info("Getting payment statistics");
        
        try {
            List<PaymentDTO> completedPayments = paymentService.getPaymentsByStatus(PaymentStatus.COMPLETED);
            List<PaymentDTO> pendingPayments = paymentService.getPaymentsByStatus(PaymentStatus.PENDING);
            List<PaymentDTO> failedPayments = paymentService.getPaymentsByStatus(PaymentStatus.FAILED);
            
            Map<String, Object> statistics = Map.of(
                "completedCount", completedPayments.size(),
                "pendingCount", pendingPayments.size(),
                "failedCount", failedPayments.size(),
                "totalPayments", completedPayments.size() + pendingPayments.size() + failedPayments.size()
            );
            
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            log.error("Error getting payment statistics: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get payment statistics for a specific loan
     */
    @GetMapping("/loan/{loanId}/statistics")
    public ResponseEntity<Map<String, Object>> getLoanPaymentStatistics(@PathVariable Long loanId) {
        log.info("Getting payment statistics for loan: {}", loanId);
        
        try {
            List<PaymentDTO> payments = paymentService.getPaymentsByLoanId(loanId);
            List<PaymentDTO> completedPayments = payments.stream()
                    .filter(p -> p.getStatus() == PaymentStatus.COMPLETED)
                    .toList();
            
            double totalPaid = completedPayments.stream()
                    .mapToDouble(p -> p.getAmount().doubleValue())
                    .sum();
            
            double totalPrincipal = completedPayments.stream()
                    .mapToDouble(p -> p.getPrincipalAmount().doubleValue())
                    .sum();
            
            double totalInterest = completedPayments.stream()
                    .mapToDouble(p -> p.getInterestAmount().doubleValue())
                    .sum();
            
            Map<String, Object> statistics = Map.of(
                "totalPayments", payments.size(),
                "completedPayments", completedPayments.size(),
                "totalPaid", totalPaid,
                "totalPrincipal", totalPrincipal,
                "totalInterest", totalInterest
            );
            
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            log.error("Error getting loan payment statistics: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
} 