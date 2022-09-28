package au.com.ailo.zombie.apocalypse.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Direction {
  RIGHT("R"),
  LEFT("L"),
  UP("U"),
  DOWN("D");

  private final String symbol;

  private static final Map<String, Direction> BY_SYMBOL = new HashMap<>();

  Direction(String symbol) {
    this.symbol = symbol;
  }

  static {
    Arrays.stream(Direction.values())
        .forEach(direction -> BY_SYMBOL.put(direction.symbol, direction));
  }

  public static Direction getDirectionBySymbol(String symbol) {
    return BY_SYMBOL.get(symbol);
  }
}
