package ru.bell.service;

import ru.bell.dao.EmailDAO;
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
