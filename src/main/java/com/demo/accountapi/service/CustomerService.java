package com.demo.accountapi.service;

import com.demo.accountapi.dto.CustomerDto;
import com.demo.accountapi.dto.convertor.CustomerDtoConvertor;
import com.demo.accountapi.exception.CustomerNotFoundException;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    private  final CustomerRepository customerRepository;
    private  final CustomerDtoConvertor convertor;


    public CustomerService(CustomerRepository customerRepository,CustomerDtoConvertor convertor) {
        this.customerRepository = customerRepository;
        this.convertor=convertor;

    }

    protected Customer findCustomerById(String id){


            Customer customer=  customerRepository.findById(id).orElseThrow(() ->  new CustomerNotFoundException("Customer is not   availbale"));
            customer.getAccounts().forEach( cstm-> System.out.println(cstm.getTransactionSet()));
            return  customer;
    }


    public CustomerDto getCustomerByid(String id){
        return convertor.convertToCustomerDto(findCustomerById(id));
    }

    public List<Customer> getAllCustomer(){
        return  customerRepository.findAll();

    }}
