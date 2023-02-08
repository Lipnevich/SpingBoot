package ru.bell.service;

import ru.bell.Application;
import ru.bell.api.UserPage;
import ru.bell.api.UserSearch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	@Test
	public void searchUserByName() {
		int total = 100;
		String prefix = "SearchableName";
		IntStream.range(0, total).forEach(index ->
				userService.create(DataGenerator.userRequest(prefix + index)));

		int pageIndex = 1;
		int pageSize = 25;
		UserPage page = null;
		do {
			page = userService.search(new UserSearch().setPage(pageIndex++).setSize(pageSize).setName(prefix));
			page.getUsers().forEach(user -> Assert.assertTrue(user.getName().contains(prefix)));
			Assert.assertEquals(total, page.getTotal().intValue());
		} while (!page.getUsers().isEmpty());

		Assert.assertEquals(total / pageSize, page.getPage() - 1);

	}

	@Test
	public void searchUserByBirthdayFrom() {
		int total = 10;
		IntStream.rangeClosed(1, total).forEach(index ->
				userService.create(DataGenerator.userRequest().setBirthday(future(index))));

		for(int i = 0; i < total; ++i) {
			UserPage page = userService.search(new UserSearch().setBirthday(future(i)));
			Assert.assertEquals(total - i, page.getTotal().intValue());
		}
	}

	@Test
	public void searchUserByPhone() {
		int total = 5;
		long prefix = 9999567890000L;
		IntStream.range(0, total).forEach(index -> userService.create(DataGenerator.userRequest()
				.setPhones(Collections.singletonList(prefix + index + ""))));

		IntStream.range(0, total).forEach(index -> {
			String phone = prefix + index + "";
			UserPage page = userService.search(new UserSearch().setPhone(phone));
			Assert.assertEquals(1, page.getTotal().intValue());
			Assert.assertTrue(page.getUsers().get(0).getPhones().stream().anyMatch(with -> with.equals(phone)));
		});

	}

	@Test
	public void searchUserByEmail() {
		int total = 5;
		IntStream.range(0, total).forEach(index -> userService.create(DataGenerator.userRequest()
				.setEmails(Collections.singletonList(email(index)))));

		IntStream.range(0, total).forEach(index -> {
			String email = email(index);
			UserPage page = userService.search(new UserSearch().setEmail(email));
			Assert.assertEquals(1, page.getTotal().intValue());
			Assert.assertTrue(page.getUsers().get(0).getEmails().stream().anyMatch(with -> with.equals(email)));
		});

	}

	private String email(int index) {
		return "email" + index + "@email.ru";
	}

	private Date future(int year) {
		String prefix = "01.01.";
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

		try {
			return format.parse(prefix + (3000 + year));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}