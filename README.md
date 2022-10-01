# Zombie Apocalypse Application

This is a Zombie Apocalypse Application which provides a set of features
covering the specification of the requirements described [here](Zombie%20v4.6.1.pdf).

The application computes and logs to terminal/console the final position of a set of Zombies and creatures.

# Table of Contents
* [Prerequisites](#prerequisites)
* [Running the test suite](#running-the-test-suite)
* [Building the source](#building-the-source)
* [Running the app from terminal](#running-the-app-from-terminal)
* [Request schema](#request-schema)
* [Code coverage](#code-coverage)
* [Code formatting](#code-formatting)
* [Application design and some design decisions](#application-design-and-some-design-decisions)
* [Movements](#movements)
* [Out of bounds movement](#out-of-bounds-movement)

## Prerequisites

- JDK 11+ or higher
- Gradle

## Running the test suite

Running this command will compile as well as run all tests

```bash
./gradlew clean test
```

## Building the source

This will download all the required dependencies, build the source and create an executable JAR file in the build/lib directory.

```bash
./gradlew clean build shadowJar
```

## Running the app from terminal

Execute the below command to build and execute the app from terminal.

```bash
./gradlew clean build shadowJar jacocoTestReport && java -jar -DrequestFile="request.json"  build/libs/zombie-apocalypse-challenge-1.0-SNAPSHOT-all.jar
```
or run the following command to execute the application alone after building

```bash
java -jar -DrequestFile="request.json"  build/libs/zombie-apocalypse-challenge-1.0-SNAPSHOT-all.jar
```

## Request schema
The request schema is a JSON formatted input to the application.
```json
{
  "moves": "RDRU",
  "gridSize": 4,
  "initialZombiePosition": {
    "x": 3,
    "y": 1
  },
  "initialCreaturePosition": [
    {
      "x": 0,
      "y": 1
    },
    {
      "x": 1,
      "y": 2
    },
    {
      "x": 1,
      "y": 1
    }
  ]
}

```

#### JVM/Command-line arguments
* Request file: `-DrequestFile="request.json"`. Checkout the sample provided here [request.json](request.json)
  > The raw input to the application.

Executing the above command will produce this output.
```log
Zombie 5cef994a moved to (0,1)
Zombie 5cef994a infected creature at (0,1)
Zombie 5cef994a moved to (0,2)
Zombie 5cef994a moved to (1,2)
Zombie 5cef994a infected creature at (1,2)
Zombie 5cef994a moved to (1,1)
Zombie 5cef994a infected creature at (1,1)

Zombie c71c9d73 moved to (1,1)
Zombie c71c9d73 moved to (1,2)
Zombie c71c9d73 moved to (2,2)
Zombie c71c9d73 moved to (2,1)

Zombie c972ebf0 moved to (2,2)
Zombie c972ebf0 moved to (2,3)
Zombie c972ebf0 moved to (3,3)
Zombie c972ebf0 moved to (3,2)

Zombie 5aba7f9b moved to (2,1)
Zombie 5aba7f9b moved to (2,2)
Zombie 5aba7f9b moved to (3,2)
Zombie 5aba7f9b moved to (3,1)

zombies’ positions: 
(1,1)(2,1)(3,2)(3,1)
creatures’ positions: 
none
```

### JSON formatted result
```log
Printing JSON formatted result
{"zombieFinalPositions":[{"x":1,"y":1},{"x":2,"y":1},{"x":3,"y":2},{"x":3,"y":1}],"creatureFinalPositions":[]}
```

## Code coverage

### Jacoco code coverage
While the goal of the test harness is to cover as much edge and corner cases, that naturally led to a wider coverage of over 85%.
Code coverage was both executed as part of gradle build cycle using [JaCoCo](https://github.com/jacoco/jacoco) and from IDE

![alt text][codeCoverageJacoco]

![alt text][codecoverage]


## Code formatting
Source code was formatted using [google-java-format](https://github.com/google/google-java-format)


## Application design and some design decisions

### Zombie ID
Every Zombie gets assigned an ID during it's life cycle. This ID is used to uniquely identify a zombie making it easy to track the path a zombie took.

The logic to derive the ZombieId is as follows
```log
UUID.randomUUID().toString().split("-")[0]
```
```log
Zombie 5cef994a moved to (0,1)
Zombie 5cef994a infected creature at (0,1)
Zombie 5cef994a moved to (0,2)
Zombie 5cef994a moved to (1,2)
Zombie 5cef994a infected creature at (1,2)
Zombie 5cef994a moved to (1,1)
Zombie 5cef994a infected creature at (1,1)

```

### [Request](src/main/java/au/com/ailo/zombie/apocalypse/types/Request.java)
This is the request object which is the input to the system. [request.json](request.json) deserializes to this.
```java
public class Request {

  private String moves;

  private int gridSize;

  private Coordinate initialZombiePosition;

  private List<Coordinate> initialCreaturePosition;
}
```

### [Direction](src/main/java/au/com/ailo/zombie/apocalypse/types/Direction.java)
R, L, U & D are represented as Direction enum.
```java
public enum Direction {
  RIGHT("R"),
  LEFT("L"),
  UP("U"),
  DOWN("D")
}
```

### [Coordinate](src/main/java/au/com/ailo/zombie/apocalypse/types/Coordinate.java)
X and Y axis are represented as coordinates.

```java
public class Coordinate {
  private int x;
  private int y;
}
```

### [InputDispatcher](src/main/java/au/com/ailo/zombie/apocalypse/input/InputDispatcher.java)
The input dispatcher, takes the raw input as command-line args, validates it and dispatches it off to the RequestHandler

### [IGrid](src/main/java/au/com/ailo/zombie/apocalypse/types/IGrid.java)
This is the Grid Interface which provides a bunch of operations that makes it possible to interact with the grid.
```java
public interface IGrid {

  int size();
  
  void infectCreature(Coordinate coordinate);
  
  void creaturefyCoordinate(Coordinate coordinate);
  
  boolean hasCreature(Coordinate coordinate);
  
  List<Coordinate> zombieLocations();
  
  List<Coordinate> creatureLocations();
  
}
```

## Movements
### Move left 
move left means decrease X-axis by 1 unit while keeping Y constant

### Move right
move right this mean increase X-axis by 1 unit while keeping Y constant

### Move down
move down this mean increase Y-axis by 1 unit while keeping X constant

### Move up
move up this mean decrease Y-axis by 1 unit while keeping X constant

## Out of bounds movement 
The size of the grid limits the possibility of movements. 
A 4 x 4 grid limits movements to any coordinates within this range `0 <= X <= 3`

### left
If next move to the left is out of bounds (invalid) then jump to the right most position.

### right
If next move to the right is out of bounds (invalid) then jump to the left most position.

### up
If next move up is out of bounds (invalid) then jump to the down most position.

### down
If next move down is out of bounds (invalid) then jump to the top most position.

[codecoverage]: screenshots/codeCoverage_Ide.png "codeCoverage_Ide"
[codeCoverageJacoco]: screenshots/codeCoverageJacoco.png "codeCoverageJacoco"