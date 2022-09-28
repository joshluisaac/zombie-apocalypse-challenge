package au.com.ailo.zombie.apocalypse.utils;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

  private Utils() {}

  public static String printResult(List<Coordinate> coordinates) {
    return coordinates
        .stream()
        .map(coordinate -> String.format("(%d,%d)", coordinate.getX(), coordinate.getY()))
        .collect(Collectors.joining());
  }
}
