# AccountApi

Spring Boot Application

This project provides to create account for existing customers.

Summary

The assessment consists of an API to be used for opening a new “current account” of already existing customers.
Requirements

• The API will expose an endpoint which accepts the user information (customerID, initialCredit).

• Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.

• Also, if initialCredit is not 0, a transaction will be sent to the new account.

• Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

The application has 2 apis

AccountAPI

CustomerAPI


POST /v1/account - creates a new account for existing customer

GET /v1/customer/{customerId} - retrieves a customer

GET /v1/customer - retrieves all customers


JUnit test coverage is 100% as well as integration tests are available.

