package com.demo.accountapi.controller;

import com.demo.accountapi.IntegrationTestSupprot;
import com.demo.accountapi.dto.CreateAccountRequest;
import com.demo.accountapi.dto.convertor.CustomerDtoConvertor;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.repository.CustomerRepository;
import com.demo.accountapi.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest extends IntegrationTestSupprot {






    @Test
    public void testCreateAccount_whenCustomerIdExits_shouldCreateAccountAndReturnAccountDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        CreateAccountRequest request = generateCreateAccountRequest(customer.getId(), 100);

        this.mockMvc.perform(post("/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))




                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.balance", equalTo(100)))
                .andExpect(jsonPath("$.customer.id", equalTo(customer.getId())))
                .andExpect(jsonPath("$.customer.name", equalTo(customer.getName())))
                .andExpect(jsonPath("$.customer.surname", equalTo(customer.getSurname())))
                .andExpect(jsonPath("$.transactions", hasSize(1)));
    }


    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExit_shouldReturn404NotFound() throws Exception {
        CreateAccountRequest request = generateCreateAccountRequest("id", 100);

        this.mockMvc.perform(post("/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testCreateAccount_whenRequestHasNoCustomerId_shouldReturn400BadRequest() throws Exception {
        CreateAccountRequest request = generateCreateAccountRequest("", 100);

        this.mockMvc.perform(post("/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testCreateAccount_whenRequestHasLessThanZeroInitialCreditValue_shouldReturn400BadRequest() throws Exception {
        CreateAccountRequest request = generateCreateAccountRequest("id", -100);

        this.mockMvc.perform(post("/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }











}
