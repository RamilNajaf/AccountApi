package com.demo.accountapi.repository;

import com.demo.accountapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {


}
