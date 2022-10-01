package au.com.ailo.zombie.apocalypse.utils;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
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

  public static void printer(String message) {
    System.out.println(message);
  }

  public static void toJson(File file, Object object) throws IOException {
    new ObjectMapper().writeValue(file, object);
  }

  public static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }
}
