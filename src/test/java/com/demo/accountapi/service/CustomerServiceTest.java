package com.demo.accountapi.service;

import com.demo.accountapi.dto.CustomerDto;
import com.demo.accountapi.dto.convertor.CustomerDtoConvertor;
import com.demo.accountapi.exception.CustomerNotFoundException;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerServiceTest {
    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConvertor convertor;


    @BeforeEach
    public void setUp() {

        customerRepository = Mockito.mock(CustomerRepository.class);
        convertor = Mockito.mock(CustomerDtoConvertor.class);
        service = new CustomerService(customerRepository, convertor) /*injectMock*/;

    }


    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {

        Customer customer = new Customer("id", "name", "surname", List.of());

        Mockito.when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));

        Customer result = service.findCustomerById("customer-id");

        assertEquals(result,
                customer);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));

    }


    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", List.of());
        CustomerDto customerDto = new CustomerDto("customer-id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        Mockito.when(convertor.convertToCustomerDto(customer)).thenReturn(customerDto);
        CustomerDto result = service.getCustomerByid("customer-id");
        assertEquals(result,
                customerDto);

    }



    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> service.getCustomerByid("id"));

        Mockito.verifyNoInteractions(convertor);
    }



    }



