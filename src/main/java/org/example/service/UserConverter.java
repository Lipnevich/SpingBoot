package org.example.service;

import org.example.api.User;
import org.example.dao.AccountEntity;
import org.example.dao.EmailEntity;
import org.example.dao.PhoneEntity;
import org.example.dao.UserEntity;

import java.util.stream.Collectors;

public class UserConverter {

    public static User from(UserEntity user) {
        return new User()
                .setId(user.getId())
                .setName(user.getName())
                .setBalance(user.getAccount().getBalance())
                .setBirthday(user.getBirthday())
                .setEmails(user.getEmails().stream().map(EmailEntity::getEmail).collect(Collectors.toList()))
                .setPhones(user.getPhones().stream().map(PhoneEntity::getPhone).collect(Collectors.toList()));
    }
    public static UserEntity from(User request) {
        UserEntity user = new UserEntity();
        return from(request, user);
    }

    public static UserEntity from(User request, UserEntity user) {
        user.setName(request.getName());
        user.setBirthday(request.getBirthday());

        return user.setId(user.getId()).setAccount(new AccountEntity().setInitialBalance(request.getBalance())
                        .setBalance(request.getBalance()).setUser(user))
                .setPassword(request.getPassword())
                .setEmails(request.getEmails().stream().map(email -> new EmailEntity().setEmail(email).setUser(user))
                        .collect(Collectors.toList()))
                .setPhones(request.getPhones().stream().map(phone -> new PhoneEntity().setPhone(phone).setUser(user))
                        .collect(Collectors.toList()));
    }
}
