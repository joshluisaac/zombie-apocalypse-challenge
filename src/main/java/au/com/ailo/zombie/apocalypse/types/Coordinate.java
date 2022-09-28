package au.com.ailo.zombie.apocalypse.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coordinate {

  private int x;

  private int y;

  public static Coordinate create(Coordinate coordinate) {
    return new Coordinate(coordinate.getX(), coordinate.getY());
  }

  public static Coordinate create(int x, int y) {
    return new Coordinate(x, y);
  }
}
