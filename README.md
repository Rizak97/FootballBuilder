# FootballBuilder
#### Coverage: 81%
# FOOTBALL BUILDER WEB APPLICATION

A fullstack website designed to allow users to build their football squad, with crud functionality. The user is able to add,update and delete entries.


## Getting Started
The procedures below will get a local copy of the project up and running for development and testing purposes. Notes on how to deploy the project on a live system, including maven packaging as well as how the frontend will be loaded after the JAR has been executed, can be found under deployment.

### Prerequisites
#### Essential
*The Java SE Development kit is required and may be downloaded from here:*
<br>https://www.oracle.com/java/technologies/downloads/<br>
*MySQL Community, as well as MySQL Workbench, is required:*
<br>https://dev.mysql.com/downloads/windows/installer/8.0.html <br>

#### Additional
*Gitbash for terminal access:*
 <br>https://git-scm.com/downloads <br>
*Spring boot IDE for API alterations:*
<br>https://spring.io/tools <br>
*VS code for any CSS, HTML or Javascript changes:*
<br>https://code.visualstudio.com/download <br>
*Apache Maven for testing and dependencies:*
 <br>https://maven.apache.org/download.cgi <br>
 <br>[JUnit](https://mvnrepository.com/artifact/junit/junit) <br>
        [Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core) <br>




### Installing
The instructions for installing and running the application are as follows:
```
Clone the repository to your local computer at a secure location using gitBash.
```

```
Open a terminal or CMD in the project folder
 - Type "java -jar HWAProject-0.0.1-SNAPSHOT.jar"
 - CRTL+C to close
```

```
The application is now running
- open a web browser
- Type "localhost8080" in URL
```
## Example Build
Starting the JAR

<img src="./HWAProject/src/main/resources/static/imgs/JARterminal.png" alt="">

Opening the web page

<img src="./HWAProject/src/main/resources/static/imgs/WebPage.png" alt="">

web page in action 
## Running the Tests
Right-click on the project in the Spring tool IDE and select "Coverage As" then "Junit," which will execute all of the tests. Alternatively, enter CMD and type "mvn clean install." This will run all of the tests and provide a coverage table indicating how much of the code was tested and what percentage passed/failed/erroneously.

### Integration Tests
Integration testing is where you test components of an application to see if they logically work together and produce the correct result.
These tests are run with JUnit and MockMvc.
An example of integration testing in the controller class is shown below:

```
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
```
### Unit Tests
Unit testing is essentially testing units of code individually and is done during the development stage.
The controller class was unit tested by mocking the the return from the service class. Mockito was used to mimick the return when service method is called. These tests are also run with JUnit, and MockMvc

```
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

```

## Built with
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning
* [GitHub](https://github.com/) for versioning.

## Authors
* **Abdirizak Osman** - 

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* *Trainer* - [AnoushLowton](https://github.com/ALowtonQA)
* *Trainer* - [JordanBenBelaid](https://github.com/jordanbenbelaid)
* *Trainer* - [Edward Reynolds](https://github.com/Edrz-96)
* *Trainer* - [Piers Barber](https://github.com/PCMBarber)
* *Testing Developer* - [JUnit](https://junit.org/junit5/docs/current/user-guide/#running-tests)
