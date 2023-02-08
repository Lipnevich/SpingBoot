package org.example.service;

import org.example.dao.PhoneEntity;
import org.example.dao.PhoneDAO;
import org.example.dao.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    @Autowired
    private PhoneDAO phoneDataDAO;

    public void add(Long userId, String phone) {
        phoneDataDAO.save(new PhoneEntity().setPhone(phone));
    }

}
