package org.example.dao;

import org.example.Application;
import org.example.service.DataGenerator;
import org.example.service.UserConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DaoTest {


	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmailDAO emailDataDAO;

	private Long userId;
	private String email;

	@Before
	public void before() throws ParseException {
		UserEntity userEntity = userDAO.save(UserConverter.from(DataGenerator.userRequest("name")));
		userId = userEntity.getId();
		email = userEntity.getEmails().get(0).getEmail();
	}

	@Test
	@Transactional
	public void addEmail() throws Exception {
		String newEmail = "DefinetelyNew@email.ru";
		emailDataDAO.add(userId, newEmail);

		Assert.assertEquals(userId, userDAO.findByEmail(newEmail).getId());
	}

	@Test
	@Transactional
	public void changeEmail() throws Exception {
		String newEmail = "TesingEmail@email.ru";
		emailDataDAO.change(userId, email, newEmail);

		Assert.assertEquals(userId, userDAO.findByEmail(newEmail).getId());
	}


}