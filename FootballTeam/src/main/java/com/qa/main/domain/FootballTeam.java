package com.qa.main.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FootballTeam {

	// Columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	@Column(nullable = false)
	private String teamName;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String Position;

	// Constructors
	// Default constructor (for Spring)
	public FootballTeam() {
	}

	// For creating (without ID)
	public FootballTeam(String teamName, String firstName, String lastName, String position) {
		super();
		this.teamName = teamName;
		this.firstName = firstName;
		this.lastName = lastName;
		Position = position;
	}

	// For reading (with ID)

	public FootballTeam(long id, String teamName, String firstName, String lastName, String position) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.firstName = firstName;
		this.lastName = lastName;
		Position = position;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	// Override methods
	// For testing

	@Override
	public int hashCode() {
		return Objects.hash(Position, firstName, id, lastName, teamName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootballTeam other = (FootballTeam) obj;
		return Objects.equals(Position, other.Position) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(teamName, other.teamName);
	}

}
