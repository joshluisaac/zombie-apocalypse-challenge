package au.com.ailo.zombie.apocalypse.utils;

import static au.com.ailo.zombie.apocalypse.types.Coordinate.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import java.util.List;
import org.junit.jupiter.api.Test;

class UtilsTest {

  @Test
  void printResult_WithCoordinates_ShouldPrintResult() {
    List<Coordinate> coordinates = List.of(create(1, 1), create(2, 1), create(3, 2), create(3, 1));
    assertEquals("(1,1)(2,1)(3,2)(3,1)", Utils.printResult(coordinates));
  }
}
