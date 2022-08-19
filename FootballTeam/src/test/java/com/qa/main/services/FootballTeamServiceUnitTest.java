package com.qa.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.FootballTeam;
import com.qa.main.repos.FootballTeamRepo;

@SpringBootTest
public class FootballTeamServiceUnitTest {
	@Autowired
	private FootballTeamService service;

	@MockBean
	private FootballTeamRepo repo;

	@Test
	public void testCreate() {
		// Create and object for saving
		FootballTeam entry = new FootballTeam("Uxbridge", "Abdul", "Omar", "Striker");

		// Create an object for the result
		FootballTeam result = new FootballTeam(10L, "Uxbridge", "Abdul", "Omar", "Striker");

		Mockito.when(repo.saveAndFlush(entry)).thenReturn(result);

		assertEquals(result, service.create(entry));
	}

	@Test
	public void testGetAll() {
		// Create and object for saving
		List<FootballTeam> result = new ArrayList<>();
		result.add(new FootballTeam(1L, "Uxbridge", "Abdul", "Omar", "Striker"));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(result, service.getAll());
	}

	@Test
	public void getByIdTest() {

		long id = 1;
		// Create an object for saving
		FootballTeam result = new FootballTeam(1L, "Uxbridge", "Abdul", "Omar", "Striker");
		Optional<FootballTeam> resultI = Optional.of(result);
		Mockito.when(repo.findById(id)).thenReturn(resultI);

		assertEquals(result, service.getByID(id));

	}

	@Test
	public void testGetTeamName() {

		String teamName = "HayesFc";
		// Create an object for saving
		List<FootballTeam> result = new ArrayList<>();
		result.add(new FootballTeam(1L, "HayesFc", "Abdul", "Omar", "Striker"));

		Mockito.when(repo.findFootballTeamByteamName(teamName)).thenReturn(result);

		assertEquals(result, service.getByteamName(teamName));

	}

	@Test
	public void updateByIdTest() {
		long id = 1L;
		FootballTeam entry = new FootballTeam("Hayes", "AB", "Omar", "RB");

		FootballTeam existing = new FootballTeam(1L, "Uxbridge", "Abdul", "Omar", "Striker");
		Optional<FootballTeam> existingF = Optional.of(existing);
		Mockito.when(repo.findById(id)).thenReturn(existingF);

		FootballTeam update = new FootballTeam(1L, "Hayes", "AB", "Omar", "RB");

		Mockito.when(repo.saveAndFlush(update)).thenReturn(update);
		assertEquals(update, service.update(id, entry));
	}

}
