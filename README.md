
# Tennis Game Project

This project implements a simplified model of a tennis game, where players can win points, games, and keep track of their scores. The game logic is primarily managed within the `Player` abd `Game` classes, but the implementation could be expanded for more complex scenarios.

## Table of Contents

- [Project Setup](#project-setup)
- [How to Start the Application](#how-to-start-the-application)
- [How to Run Tests](#how-to-run-tests)
- [Note on Game State Management](#note-on-game-state-management)

## Project Setup

This project uses Maven for dependency management. To get started, follow the steps below:

### Frameworks and Libraries Used

- **JUnit 5 (Jupiter)**: Default testing framework for unit and integration tests.
- **Mockito**: Used for mocking dependencies and objects in tests.
- **AssertJ**: Used for advanced and readable assertions in tests.

### Prerequisites

Make sure you have the following installed:

- [Java 17+](https://adoptopenjdk.net/) (or later)
- [Maven](https://maven.apache.org/) (version 3.6+)

### Clone the Repository

Clone the project to your local machine:

```bash
git clone https://github.com/tenniskata/tennisgame
cd tennisgame
```

### Package 

To compile the project and generate an executable JAR with all dependencies, run the following command:

```bash
mvn clean package
```
This will produce a .jar file in the target/ directory, e.g.: target/tennis-game-1.0-SNAPSHOT-jar-with-dependencies.jar


## How to Start the Application

Next, run the generated JAR with:

```bash
java -jar target/tennis-game-1.0-SNAPSHOT-jar-with-dependencies.jar "points_sequence"
```

Replace `"points_sequence"` with the desired sequence of points. The argument must be a string consisting only of the characters `A` (for player A) and `B` (for player B), representing the points won in the game.

## How to Run Tests

To run the tests for this project, execute the following Maven command:

```bash
mvn test
```

This will run all tests.

## Note on Game State Management

An alternative approach would have been to manage the game state at the `Game` class level, which would allow centralizing the logic, including managing advantages and state transitions between players. However, the state has been managed directly within the `Player` object to simplify the implementation and provide more flexibility in the short term.
