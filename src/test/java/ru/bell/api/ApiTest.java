package ru.bell.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import ru.bell.Application;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bell.service.DataGenerator;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class ApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserApi api;

	private ObjectWriter jsonWriter;

	@Before
	public void before() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		jsonWriter = mapper.writer().withDefaultPrettyPrinter();
	}

	@Test
	public void validationUserRequest() throws Exception {
		this.mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(userJson(null))).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void userCreation() throws Exception {
		String name = "UniqueNameForUserCreationTest";

		this.mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(userJson(name))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(name)));

		this.mockMvc.perform(post("/user/search").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(searchJson(name))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(name)));
	}

	private String userJson(String name) throws JsonProcessingException {
		return jsonWriter.writeValueAsString(DataGenerator.userRequest(name));
	}

	private String searchJson(String name) throws JsonProcessingException {
		return jsonWriter.writeValueAsString(new UserSearch().setName(name));
	}
}