package org.example.service;

import org.example.dao.EmailDAO;
import org.example.dao.EmailEntity;
import org.example.dao.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    @Autowired
    private EmailDAO emailDataDAO;

    @Transactional
    public void add(Long userId, String email) {
        emailDataDAO.add(userId, email);
    }

    @Transactional
    public void change(Long userId, String oldEmail, String newEmail) {
        emailDataDAO.change(userId, oldEmail, newEmail);
    }

    public void remove(String email) {
        emailDataDAO.delete(email);
    }
}
