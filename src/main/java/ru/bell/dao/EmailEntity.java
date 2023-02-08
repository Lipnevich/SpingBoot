package ru.bell.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "EMAIL_DATA")
public class EmailEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    private UserEntity user;


    @Column(name = "EMAIL", length = 200, nullable = false)
    private String email;

    public Long getId() {
        return id;
    }

    public EmailEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmailEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public EmailEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailEntity entity = (EmailEntity) o;
        return id.equals(entity.id) && email.equals(entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}