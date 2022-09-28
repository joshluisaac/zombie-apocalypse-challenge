package au.com.ailo.zombie.apocalypse.handler;

import static au.com.ailo.zombie.apocalypse.types.Coordinate.create;
import static au.com.ailo.zombie.apocalypse.types.Direction.getDirectionBySymbol;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import au.com.ailo.zombie.apocalypse.Grid;
import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.IGrid;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RequestHandlerTest {

  @ParameterizedTest
  @MethodSource("dataProvider")
  void handleMoves_WithValidArgs_ShouldShiftZombieToNextPosition(
      int gridSize, String directionSymbol, Coordinate curr, Coordinate expected) {
    IGrid grid = new Grid(gridSize);
    RequestHandler requestHandler = new RequestHandler(grid, new ArrayDeque<>());
    requestHandler.handleMoves(getDirectionBySymbol(directionSymbol), new AtomicReference<>(curr));
    assertEquals(expected, curr);
  }

  private static Stream<Arguments> dataProvider() {
    return Stream.of(
        of(4, "L", create(0, 0), create(3, 0)),
        of(4, "L", create(0, 1), create(3, 1)),
        of(4, "L", create(0, 2), create(3, 2)),
        of(4, "L", create(0, 3), create(3, 3)),
        of(4, "R", create(3, 0), create(0, 0)),
        of(4, "R", create(3, 1), create(0, 1)),
        of(4, "R", create(3, 2), create(0, 2)),
        of(4, "R", create(3, 3), create(0, 3)),
        of(4, "U", create(0, 0), create(0, 3)),
        of(4, "U", create(1, 0), create(1, 3)),
        of(4, "U", create(2, 0), create(2, 3)),
        of(4, "U", create(3, 0), create(3, 3)),
        of(4, "D", create(0, 3), create(0, 0)),
        of(4, "D", create(1, 3), create(1, 0)),
        of(4, "D", create(2, 3), create(2, 0)),
        of(4, "D", create(3, 3), create(3, 0)),
        of(10, "L", create(0, 4), create(9, 4)),
        of(10, "D", create(3, 9), create(3, 0)),
        of(4, "D", create(3, 0), create(3, 1)),
        of(4, "U", create(2, 2), create(2, 1)),
        of(4, "L", create(3, 0), create(2, 0)),
        of(4, "R", create(1, 3), create(2, 3)));
  }
}
