package au.com.ailo.zombie.apocalypse.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tuple<L, R> {

  private L left;
  private R right;
}
