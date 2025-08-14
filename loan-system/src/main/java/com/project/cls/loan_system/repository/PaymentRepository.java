package com.project.cls.loan_system.repository;

import com.project.cls.loan_system.entity.PaymentEntity;
import com.project.cls.loan_system.lib.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    
    List<PaymentEntity> findByLoanId(Long loanId);
    
    Page<PaymentEntity> findByLoanId(Long loanId, Pageable pageable);
    
    List<PaymentEntity> findByStatus(PaymentStatus status);
    
    List<PaymentEntity> findByLoanIdAndStatus(Long loanId, PaymentStatus status);
    
    @Query("SELECT p FROM PaymentEntity p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<PaymentEntity> findByPaymentDateRange(@Param("startDate") LocalDateTime startDate, 
                                              @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT p FROM PaymentEntity p WHERE p.loan.user.id = :userId")
    List<PaymentEntity> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(p.amount) FROM PaymentEntity p WHERE p.loan.id = :loanId AND p.status = 'COMPLETED'")
    BigDecimal getTotalPaidAmountByLoanId(@Param("loanId") Long loanId);
    
    @Query("SELECT p FROM PaymentEntity p WHERE p.loan.id = :loanId ORDER BY p.paymentNumber DESC LIMIT 1")
    PaymentEntity findLastPaymentByLoanId(@Param("loanId") Long loanId);
    
    @Query("SELECT COUNT(p) FROM PaymentEntity p WHERE p.loan.id = :loanId AND p.status = 'COMPLETED'")
    Long countCompletedPaymentsByLoanId(@Param("loanId") Long loanId);
} 