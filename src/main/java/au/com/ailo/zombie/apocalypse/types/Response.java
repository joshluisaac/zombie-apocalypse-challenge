package au.com.ailo.zombie.apocalypse.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {

  List<Coordinate> zombieFinalPositions;
  List<Coordinate> creatureFinalPositions;
}
