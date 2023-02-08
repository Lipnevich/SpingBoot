package ru.bell.dao;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne(mappedBy = "user", optional = false, cascade = CascadeType.ALL)
    private AccountEntity account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Size(min = 1)
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PhoneEntity> phones;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Size(min = 1)
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<EmailEntity> emails;


    @Column(name = "NAME", length = 500, nullable = false)
    private String name;

    @Column(name = "PASSWORD", length = 500, nullable = false)
    @Size(min = 8, max = 500)
    private String password;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;

    public UserEntity() {}

    public UserEntity(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public UserEntity setAccount(AccountEntity account) {
        this.account = account;
        return this;
    }

    public Set<EmailEntity> getEmails() {
        return emails;
    }

    public UserEntity setEmails(Set<EmailEntity> emailData) {
        this.emails = emailData;
        return this;
    }

    public Set<PhoneEntity> getPhones() {
        return phones;
    }

    public UserEntity setPhones(Set<PhoneEntity> phoneData) {
        this.phones = phoneData;
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", account=" + account +
                ", phones=" + phones +
                ", emails=" + emails +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}