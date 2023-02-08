package ru.bell.service;

import ru.bell.dao.PhoneEntity;
import ru.bell.dao.PhoneDAO;
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
