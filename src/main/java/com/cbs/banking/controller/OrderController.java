package com.cbs.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.banking.model.Order;
import com.cbs.banking.model.Payment;
import com.cbs.banking.model.Project;
import com.cbs.banking.model.Vendor;
import com.cbs.banking.repository.OrderRepository;
import com.cbs.banking.repository.PaymentRepository;
import com.cbs.banking.repository.ProjectRepository;
import com.cbs.banking.repository.VendorRepository;

@CrossOrigin
@RestController
public class OrderController {

	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	
	@PostMapping("/order/{vid}/{pid}")
	public Order postOrder(@RequestBody Order order, @PathVariable("vid") Long vid,
			@PathVariable("pid") Long pid) {
		
		Vendor vendor = vendorRepository.getById(vid);
		order.setVendor(vendor);
		
		order.setStatus("PENDING");
		
		Project project = projectRepository.getById(pid);
		order.setProject(project);
		
		Payment payment = new Payment();
		payment.setStatus("UNPAID");
		payment = paymentRepository.save(payment);
		
		order.setPayment(payment);
		return orderRepository.save(order);
	}
	
	@GetMapping("/order/pending")
	public List<Order> getPendingOrders() {
		return orderRepository.fetchOrderByStatus("PENDING"); 
	}
	
	@GetMapping("/order/completed")
	public List<Order> getCompletedOrders() {
		return orderRepository.fetchOrderByStatus("COMPLETED"); 
	}
	
	@PutMapping("/order/status/{id}")
	public void changeStatus(@PathVariable("id") Long id) {
		orderRepository.updateStatus(id, "COMPLETED");
	}
	
	@GetMapping("/order")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
}
