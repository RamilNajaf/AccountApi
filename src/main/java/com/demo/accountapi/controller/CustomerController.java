package com.demo.accountapi.controller;


import com.demo.accountapi.dto.CustomerDto;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.service.CustomerService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String customer_id){
        return ResponseEntity.ok(customerService.getCustomerByid(customer_id));
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
         return customerService.getAllCustomer();
    }

}
