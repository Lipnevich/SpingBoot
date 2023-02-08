package org.example.api;

import java.util.Date;

public class UserSearch {

    private Integer size;
    private Integer page;
    private Date birthday;
    private String name;
    private String phone;
    private String email;

    public Integer getSize() {
        return size;
    }

    public UserSearch setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public UserSearch setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserSearch setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserSearch setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserSearch setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSearch setEmail(String email) {
        this.email = email;
        return this;
    }
}
