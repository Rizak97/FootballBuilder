package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
}
