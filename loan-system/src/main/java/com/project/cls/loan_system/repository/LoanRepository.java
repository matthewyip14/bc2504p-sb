package com.project.cls.loan_system.repository;

import com.project.cls.loan_system.entity.LoanEntity;
import com.project.cls.loan_system.lib.LoanStatus;
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
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    
    List<LoanEntity> findByUserId(Long userId);
    
    Page<LoanEntity> findByUserId(Long userId, Pageable pageable);
    
    List<LoanEntity> findByStatus(LoanStatus status);
    
    Page<LoanEntity> findByStatus(LoanStatus status, Pageable pageable);
    
    List<LoanEntity> findByUserIdAndStatus(Long userId, LoanStatus status);
    
    @Query("SELECT l FROM LoanEntity l WHERE l.loanAmount BETWEEN :minAmount AND :maxAmount")
    List<LoanEntity> findByLoanAmountRange(@Param("minAmount") BigDecimal minAmount, 
                                          @Param("maxAmount") BigDecimal maxAmount);
    
    @Query("SELECT l FROM LoanEntity l WHERE l.applicationDate BETWEEN :startDate AND :endDate")
    List<LoanEntity> findByApplicationDateRange(@Param("startDate") LocalDateTime startDate, 
                                               @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT l FROM LoanEntity l WHERE l.user.id = :userId AND l.remainingBalance > 0")
    List<LoanEntity> findActiveLoansByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(l.loanAmount) FROM LoanEntity l WHERE l.status = :status")
    BigDecimal getTotalLoanAmountByStatus(@Param("status") LoanStatus status);
    
    @Query("SELECT COUNT(l) FROM LoanEntity l WHERE l.status = :status")
    Long countByStatus(@Param("status") LoanStatus status);
} 