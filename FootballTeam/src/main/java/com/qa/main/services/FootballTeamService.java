package com.qa.main.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.qa.main.domain.FootballTeam;
import com.qa.main.exceptions.FootballTeamNotFoundException;
import com.qa.main.repos.FootballTeamRepo;

@Service
public class FootballTeamService {
	private FootballTeamRepo repo;

	public FootballTeamService(FootballTeamRepo repo) {
		this.repo = repo;
	}

	public FootballTeam create(FootballTeam football) {
		return repo.saveAndFlush(football);
	}

	public List<FootballTeam> getAll() {
		return repo.findAll();
	}

	public FootballTeam getByID(long id) {
		return repo.findById(id).orElseThrow(FootballTeamNotFoundException::new);
	}

	public List<FootballTeam> getByteamName(String teamName) {
		return repo.findFootballTeamByteamName(teamName);
	}

	public FootballTeam update(long id, FootballTeam football) {
		// We get the existing entry
		FootballTeam existing = repo.findById(id).get();

		// Update the existing entry, to match the incoming object
		existing.setTeamName(football.getTeamName());
		existing.setFirstName(football.getFirstName());
		existing.setLastName(football.getLastName());
		existing.setPosition(football.getPosition());

		// Save the updated entry back into the DB (ID is the same)
		return repo.saveAndFlush(existing);

	}

	public boolean delete(long id) {
		repo.deleteById(id);

		return !repo.existsById(id);
	}
}
