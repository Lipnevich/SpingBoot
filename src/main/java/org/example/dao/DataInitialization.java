package org.example.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitialization implements ApplicationRunner {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private EmailDAO emailDAO;
    @Autowired
    private PhoneDAO phoneDAO;

    private static final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws ParseException {
//        if (true) return;
        userDAO.deleteAll();

        long count = userDAO.count();

        if (count == 0) {
            createUser("John", "JohnSecret", df.parse("20.12.1980"),
                    BigDecimal.TEN, "email@email.com", "1234567890123");

            createUser("Smith" ,"SmithWillBeBack", df.parse("21.11.1985"),
                    BigDecimal.ONE,"email@email.ru", "1234567890124");
        }

    }

    private void createUser(String name, String password, java.util.Date birthday, BigDecimal balance, String email, String phone) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(password);
        user.setBirthday(birthday);
        userDAO.save(user);

        AccountEntity account = new AccountEntity().setBalance(balance).setInitialBalance(balance).setUser(user);
        accountDAO.save(account);

        EmailEntity emailData = new EmailEntity().setEmail(email).setUser(user);
        emailDAO.save(emailData);

        PhoneEntity phoneData = new PhoneEntity().setPhone(phone).setUser(user);
        phoneDAO.save(phoneData);
    }

}