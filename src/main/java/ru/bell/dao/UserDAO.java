package ru.bell.dao;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Long> {

    @Cacheable("users")
    Page<UserEntity> findByNameStartsWith(String name, Pageable pageable);

    @Query(value="from UserEntity u INNER JOIN u.phones p WHERE p.phone = :phone")
    @Cacheable("users")
    UserEntity findByPhone(String phone);

    @Query(value="SELECT COUNT(*) from User WHERE name LIKE :name%", nativeQuery = true)
    @Cacheable("counts")
    Integer countUsersWithNameLike(String name);

    @Query(value="SELECT * FROM User WHERE name LIKE :name% OFFSET :size * (:page - 1) LIMIT :size", nativeQuery = true)
    @Cacheable("users")
    List<UserEntity> findUsersWithNameLike(String name, Integer page, Integer size);

    @Query(value="SELECT COUNT(*) from User u WHERE u.birthday > :birthday", nativeQuery = true)
    @Cacheable("counts")
    Integer countUsersWithBirthdayFrom(Date birthday);

    @Query(value="SELECT * FROM User WHERE birthday > :birthday OFFSET :size * (:page - 1) LIMIT :size", nativeQuery = true)
    @Cacheable("users")
    List<UserEntity> findUsersWithBirthdayFrom(Date birthday, Integer page, Integer size);

}
