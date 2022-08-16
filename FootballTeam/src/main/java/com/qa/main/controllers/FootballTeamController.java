package com.qa.main.controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.main.domain.FootballTeam;
import com.qa.main.services.FootballTeamService;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class FootballTeamController {
private FootballTeamService service;
	
	public FootballTeamController(FootballTeamService service) {
		this.service = service;
	}
	
	// POST REQUESTS - CREATE
	@PostMapping("/create")
	public ResponseEntity<FootballTeam> create(@RequestBody FootballTeam football) {
		return new ResponseEntity<FootballTeam>(service.create(football), HttpStatus.CREATED);
	}
	
	// GET REQUESTS - READ
	@GetMapping("/getAll")
	public ResponseEntity<List<FootballTeam>> getAll() {
		return new ResponseEntity<List<FootballTeam>>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getByID/{id}")
	public FootballTeam getByID(@PathVariable long id) {
		return service.getByID(id);
	}
	
	
	
	@GetMapping("/getByTeamName/{team_name}")
	public ResponseEntity<List<FootballTeam>> getByLastName(@PathVariable String team_name) {
		return new ResponseEntity<List<FootballTeam>>(service.getByteamName(team_name), HttpStatus.OK);
	}
	
	// PUT REQUESTS - UPDATE
	@PutMapping("/update/{id}")
	public FootballTeam update(@PathVariable long id, @RequestBody FootballTeam football) {
		return service.update(id, football);
	}
	
	// DELETE REQUESTS - DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.NO_CONTENT);
	}
	

}
