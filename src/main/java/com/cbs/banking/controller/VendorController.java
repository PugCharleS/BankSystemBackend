package com.cbs.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.banking.model.Vendor;
import com.cbs.banking.repository.VendorRepository;

@RestController
public class VendorController {

	@Autowired
	private VendorRepository vendorRepository;
	
	@PostMapping("/vendor/add")
	public Vendor addVendor(@RequestBody Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
	@GetMapping("/vendor")
	public List<Vendor> showAllVendors() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/vendor/{id}")
	public  Vendor  getOneVendor(@PathVariable("id") Long id) {
		return vendorRepository.getById(id);
	}
	
	@GetMapping("/vendor/stats")
	public Map<String,Integer>  vendorStats() {
		Map<String,Integer> map = new HashMap<>();
		List<Vendor> list = vendorRepository.findAll();
 		Map<String, List<Vendor>> map1= list.stream()
 											.collect(Collectors.groupingBy(Vendor :: getSpecs));
		
		for(String key : map1.keySet()) {
			map.put(key, map1.get(key).size());
		}
		return map; 
	}
}
