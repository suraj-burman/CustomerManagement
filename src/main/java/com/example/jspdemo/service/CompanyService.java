package com.example.jspdemo.service;

import com.example.jspdemo.model.Customer;
import com.example.jspdemo.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository customerRepo;

    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        customerRepo.findAll().forEach(customer -> customerList.add(customer));

        return customerList;
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).get();
    }

    public boolean saveOrUpdateCustomer(Customer customer) {
        Customer updatedCustomer = customerRepo.save(customer);

        if (customerRepo.findById(updatedCustomer.getId()) != null) {
            return true;
        }

        return false;
    }

    public boolean deleteCustomer(Long id) {
        customerRepo.deleteById(id);

        if (customerRepo.findById(id) != null) {
            return true;
        }

        return false;
    }

}
