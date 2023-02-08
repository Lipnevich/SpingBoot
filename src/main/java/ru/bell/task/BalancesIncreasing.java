package ru.bell.task;

import ru.bell.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
public class BalancesIncreasing {

    static final Logger LOGGER = Logger.getLogger(BalancesIncreasing.class.getName());

    @Autowired
    private AccountDAO accountDAO;

    @Scheduled(initialDelay = 3000, fixedRate = 30000)
    @Async
    @Transactional
    public void incrementClientsBalance() {
        accountDAO.increaseBalances(new BigDecimal("1.1"), new BigDecimal("2.07"));
        LOGGER.info("Balance for all clients was 10% increased");
    }
}
