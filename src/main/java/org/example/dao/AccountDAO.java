package org.example.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountDAO extends CrudRepository<AccountEntity, Long> {

    @Query(value = "UPDATE AccountEntity SET balance = balance * :multiplier " +
            "where balance * :multiplier < initialBalance * :limitMultiplier")
    @Modifying
    int increaseBalances(BigDecimal multiplier, BigDecimal limitMultiplier);

}