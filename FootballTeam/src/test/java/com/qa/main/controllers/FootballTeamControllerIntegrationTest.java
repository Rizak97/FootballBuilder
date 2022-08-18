package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.FootballTeam;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql",
		"classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // Switching to H2, for the test
public class FootballTeamControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper; // Used for converting objects to JSON

	@Test
	public void createTest() throws Exception {
		// Create an object for posting
		FootballTeam entry = new FootballTeam("Uxbridge", "Abdul", "Omar", "Striker");
		String entryAsJSON = mapper.writeValueAsString(entry);

		// Create an object for checking the result
		FootballTeam result = new FootballTeam(5L, "Uxbridge", "Abdul", "Omar", "Striker");
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(post("/team/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void readAllTest() throws Exception {
		// Create a list to check the output of readAll
		List<FootballTeam> result = new ArrayList<>();
		// Add the single entry to the list
		result.add(new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB"));
		// Convert the list to JSON (The API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(get("/team/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void readByIdTest() throws Exception {
		FootballTeam F = new FootballTeam(1L, "Hayes", "AB", "Omar", "RB");
		String FootballTeamJSON = mapper.writeValueAsString(F);
		mvc.perform(get("/team/getByID/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(FootballTeamJSON));

	}
//	@Test
//	public void readByTeamName() throws Exception {
//		
//		List<FootballTeam> result = new ArrayList<>();
//		// Add the single entry to the list
//		result.add(new FootballTeam(1L, "Hayes", "Ak", "Osman", "LB"));
//		// Convert the list to JSON (The API responds in JSON)
//		String resultAsJSON = mapper.writeValueAsString(result);
//		
//		mvc.perform(get("/team/getByTeamName/Hayes")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(content().json(resultAsJSON));
//		
//	}

	@Test
	public void updateTest() throws Exception {
		FootballTeam update = new FootballTeam(1L, "Hayes", "AB", "Omar", "RB");
		String updateJSON = mapper.writeValueAsString(update);

		FootballTeam expected = new FootballTeam(1L, "Hayes", "AB", "Omar", "RB");
		String expectedJSON = mapper.writeValueAsString(expected);

		mvc.perform(put("/team/update/1").contentType(MediaType.APPLICATION_JSON).content(updateJSON))
				.andExpect(content().json(expectedJSON));
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/team/delete/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
}
