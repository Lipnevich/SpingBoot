package ru.bell.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDAO extends CrudRepository<EmailEntity, Long> {

    @Modifying
    @Query(value = "UPDATE EmailEntity SET email = :newEmail WHERE email = :oldEmail AND email.user.id = :userId")
    int change(Long userId, String oldEmail, String newEmail);

    @Modifying
    @Query(value = "INSERT INTO EMAIL_DATA (USER_ID, EMAIL) VALUES (:userId, :email)", nativeQuery = true)
    int add(Long userId, String email);

    @Modifying
    @Query(value = "DELETE FROM EMAIL_DATA WHERE email = :email", nativeQuery = true)
    void delete(String email);
}