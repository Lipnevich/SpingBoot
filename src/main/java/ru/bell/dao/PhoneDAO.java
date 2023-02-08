package ru.bell.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDAO extends CrudRepository<PhoneEntity, Long> {

}