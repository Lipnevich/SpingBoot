package ru.bell.api;

import java.util.ArrayList;
import java.util.List;

public class UserPage {

    public static final int DEFAULT_SIZE = 25;

    private List<User> users = new ArrayList<>();
    private Integer total = 0;
    private Integer size = DEFAULT_SIZE;
    private Integer page = 1;

    public List<User> getUsers() {
        return users;
    }

    public UserPage setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public UserPage setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public UserPage setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public UserPage setPage(Integer page) {
        this.page = page;
        return this;
    }
}
