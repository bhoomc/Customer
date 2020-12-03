package com.digitalacademy.customer.service;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findAllById(id);
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer){
        Customer targetCustomer = customerRepository.findAllById(id);

        if (targetCustomer != null)
            return customerRepository.save(updatedCustomer);
        else
            return null;
    }

    public boolean deleteCustomerById(Long id){
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            System.out.println("Error delete customer id " + id + ">Error log: " + e);
            return false;
        }
    }
}
