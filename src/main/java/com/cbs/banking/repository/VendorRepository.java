package com.cbs.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbs.banking.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{

}
