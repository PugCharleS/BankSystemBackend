package com.cbs.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.banking.model.Payment;
import com.cbs.banking.repository.OrderRepository;
import com.cbs.banking.repository.PaymentRepository;

@CrossOrigin
@RestController
public class PaymentController {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@PostMapping("/payment")
	public Payment postPayment(@RequestBody Payment payment) {
		return paymentRepository.save(payment);
	}
	
	@PutMapping("/order/payment/status/{id}")
	public void changeStatus(@PathVariable("id") Long id) {
		paymentRepository.updateStatus(id, "PAID");
		orderRepository.updateStatus(id, "COMPLETED");
	}
}
