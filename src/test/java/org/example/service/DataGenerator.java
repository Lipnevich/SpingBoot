package org.example.service;

import org.example.api.User;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

    private static int index = 1_000_000;

    public static User userRequest(String name) {
        return new User().setName(name)
                .setPassword("password")
                .setBirthday(new Date(System.currentTimeMillis() - 1_000_000))
                .setBalance(BigDecimal.TEN)
                .setEmails(Stream.of("email" + uniqueIndex() + "@email.ru"
                ).collect(Collectors.toList()))
                .setPhones(Stream.of("7920" + uniqueIndex()
                ).collect(Collectors.toList()));
    }

    private static int uniqueIndex() {
        return ++index;
    }

    public static User userRequest() {
        return userRequest("name" + uniqueIndex());
    }
}
