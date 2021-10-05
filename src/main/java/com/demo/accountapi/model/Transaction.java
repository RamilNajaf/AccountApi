package com.demo.accountapi.model;

import com.demo.accountapi.enums.TransactionType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private TransactionType type = TransactionType.INITIAL;
    private BigDecimal amount;
    private LocalDateTime transactionDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction( BigDecimal amount, LocalDateTime  transactionDate,  Account account) {
                this.id = null;
                this.amount = amount;
                this.transactionDate = transactionDate;
                this.type = TransactionType.INITIAL;
                this.account=account;

    }


}
