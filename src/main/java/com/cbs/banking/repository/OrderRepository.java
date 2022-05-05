package com.cbs.banking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cbs.banking.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("select o from Order o where UPPER(o.status)=UPPER(?1)")
	List<Order> fetchOrderByStatus(String status);

	@Modifying
	@Transactional
	@Query("update Order o SET o.status=?2 where o.id=?1")
	void updateStatus(Long id, String status);
}
