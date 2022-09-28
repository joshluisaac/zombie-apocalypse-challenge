package au.com.ailo.zombie.apocalypse.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CoordinateTest {

  @Test
  void create_WithCoordinates_ShouldCreateNewCoordinate() {
    Coordinate coordinate = Coordinate.create(new Coordinate(100, 200));
    assertEquals(100, coordinate.getX());
    assertEquals(200, coordinate.getY());
  }
}
