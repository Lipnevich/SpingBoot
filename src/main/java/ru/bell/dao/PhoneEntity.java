package ru.bell.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "PHONE_DATA")
public class PhoneEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(unique = true, name = "PHONE", length = 13, nullable = false)
    private String phone;

    public Long getId() {
        return id;
    }

    public PhoneEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PhoneEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public PhoneEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}