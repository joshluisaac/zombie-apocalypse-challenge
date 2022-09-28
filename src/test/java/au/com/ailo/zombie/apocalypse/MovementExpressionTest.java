package au.com.ailo.zombie.apocalypse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import au.com.ailo.zombie.apocalypse.types.Direction;
import java.util.List;
import org.junit.jupiter.api.Test;

class MovementExpressionTest {

  @Test
  void parse_WithValidExpression_ShouldUnpackExpression() {
    List<Direction> moves = MovementExpression.parse("RDRU");
    assertEquals(Direction.RIGHT, moves.get(0));
    assertEquals(Direction.DOWN, moves.get(1));
    assertEquals(Direction.RIGHT, moves.get(2));
    assertEquals(Direction.UP, moves.get(3));
  }

  @Test
  void parse_WithInValidExpression_ShouldThrowError() {
    Throwable throwable =
        assertThrows(IllegalArgumentException.class, () -> MovementExpression.parse("X"));
    assertEquals("X is an illegal direction", throwable.getMessage());
  }
}
