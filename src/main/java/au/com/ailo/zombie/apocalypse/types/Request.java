package au.com.ailo.zombie.apocalypse.types;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Request {

  private String moves;

  private int gridSize;

  private Coordinate initialZombiePosition;

  private List<Coordinate> initialCreaturePosition;
}
