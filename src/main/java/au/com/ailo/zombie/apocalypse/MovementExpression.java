package au.com.ailo.zombie.apocalypse;

import au.com.ailo.zombie.apocalypse.types.Direction;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovementExpression {

  private static final String REGEX_PATTERN = "";

  private static final List<String> LEGAL_DIRECTION_SYMBOLS = List.of("R", "L", "U", "D");

  private MovementExpression() {}

  public static List<Direction> parse(String moveExpression) {
    String[] rawMoves = moveExpression.split(REGEX_PATTERN);

    Arrays.stream(rawMoves).forEach(MovementExpression::checkSymbol);

    return Arrays.stream(rawMoves)
        .map(Direction::getDirectionBySymbol)
        .collect(Collectors.toList());
  }

  private static void checkSymbol(String m) {
    if (!LEGAL_DIRECTION_SYMBOLS.contains(m))
      throw new IllegalArgumentException(String.format("%s is an illegal direction", m));
  }
}
