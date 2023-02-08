package ru.bell.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello world!")));
	}

	@Test
	public void validationUserRequest() throws Exception {
		this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(userJson(null))).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void creationOfUser() throws Exception {
		String name = "Name";

		this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(userJson(name))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("User created")));

		this.mockMvc.perform(get("/list")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(name)));
	}

	@Test
	public void userCreation() throws Exception {
		String name = userJson("");

		this.mockMvc.perform(post("/update").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(userJson(name))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("User created")));

		this.mockMvc.perform(get("/list")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(name)));
	}


	private String userJson(String name) throws JsonProcessingException, ParseException {
		User userRequest = DataGenerator.userRequest(name);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(userRequest);
		return requestJson;
	}
}