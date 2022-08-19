package com.qa.main.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.FootballTeam;

@Repository
public interface FootballTeamRepo extends JpaRepository<FootballTeam, Long> {

	// SELECT * FROM customer WHERE team_name = {team_name}
		List<FootballTeam> findFootballTeamByteamName(String teamName);
		
}
