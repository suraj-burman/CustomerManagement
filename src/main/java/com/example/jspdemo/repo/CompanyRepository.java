package com.example.jspdemo.repo;

import com.example.jspdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Customer, Long> {
}
