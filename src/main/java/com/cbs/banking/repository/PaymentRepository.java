package com.cbs.banking.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cbs.banking.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Payment p SET p.status=?2 WHERE p.id IN (SELECT o.payment.id FROM Order o WHERE o.id=?1)")
	void updateStatus(Long id, String status);
	
}
