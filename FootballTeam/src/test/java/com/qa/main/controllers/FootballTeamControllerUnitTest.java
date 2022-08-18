package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.FootballTeam;
import com.qa.main.services.FootballTeamService;

@WebMvcTest
public class FootballTeamControllerUnitTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private FootballTeamService service;

	@Test
	void createTest() throws Exception {
		// Create an object for posting
		FootballTeam entry = new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB");
		String entryAsJSON = mapper.writeValueAsString(entry);

		// Create an object for checking the result
		FootballTeam result = new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB");
		String resultAsJSON = mapper.writeValueAsString(result);

		Mockito.when(service.create(entry)).thenReturn(result);

		mvc.perform(post("/team/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	void readAllTest() throws Exception {
		// Create a list to check the output of readAll
		List<FootballTeam> result = new ArrayList<>();
		// Add the single entry to the list
		result.add(new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB"));

		Mockito.when(service.getAll()).thenReturn(result);

		// converts the list to a JSON (As api responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		mvc.perform(get("/team/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void updateTest() throws Exception {
		Long id = 1L;
		FootballTeam update = new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB");
		String updateJSON = mapper.writeValueAsString(update);

		FootballTeam expected = new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB");
		String expectedJSON = mapper.writeValueAsString(expected);

		Mockito.when(service.update(id, update)).thenReturn(expected);

		mvc.perform(put("/team/update/1").contentType(MediaType.APPLICATION_JSON).content(updateJSON))
				.andExpect(content().json(expectedJSON));
	}

}
