package com.demo.accountapi;

import com.demo.accountapi.model.Account;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class AccountApiApplication  implements CommandLineRunner {



    private final CustomerRepository customerRepository;



    public AccountApiApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Customer customer = customerRepository.save(new Customer("Ramil", "Najaf"));
        Customer customer2 = customerRepository.save(new Customer("FILANKAS", "FILANKASOV"));

        System.out.println(customer);
        System.out.println(customer2);

    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
