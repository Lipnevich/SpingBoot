package org.example.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "BALANCE", nullable = false)
    @Min(0)
    private BigDecimal balance;

    @Column(name = "INITIAL_BALANCE", nullable = false)
    @Min(0)
    private BigDecimal initialBalance;

    public Long getId() {
        return id;
    }

    public AccountEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public AccountEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public AccountEntity setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountEntity setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}