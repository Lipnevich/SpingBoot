package ru.bell.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class User {

    private Long id;

    @NotEmpty(message = "The user's name is required.")
    @Size(min = 2, max = 500, message = "The length of name must be between 2 and 500 characters.")
    private String name;
    @NotEmpty(message = "The user's password is required.")
    @Size(min = 8, max = 500, message = "The length of password must be between 8 and 500 characters.")
    private String password;
    @NotNull(message = "The date of birth is required.")
    @PastOrPresent(message = "The date of birth must be in the past or present.")
    private Date birthday;
    @NotNull(message = "The email is required.")
    @NotEmpty(message = "The emails must have at least one item.")
    private List<String> emails;
    @NotNull(message = "The phone is required.")
    @NotEmpty(message = "The phones must have at least one item.")
    private List<String> phones;
    @NotNull(message = "The user's balance is required.")
    @PositiveOrZero(message = "The user's balance must be greater or equal 0")
    private BigDecimal balance;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public User setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public List<String> getEmails() {
        return emails;
    }

    public User setEmails(List<String> emails) {
        this.emails = emails;
        return this;
    }

    public List<String> getPhones() {
        return phones;
    }

    public User setPhones(List<String> phones) {
        this.phones = phones;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }
}
