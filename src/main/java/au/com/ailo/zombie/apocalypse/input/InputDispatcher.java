package au.com.ailo.zombie.apocalypse.input;

import au.com.ailo.zombie.apocalypse.Grid;
import au.com.ailo.zombie.apocalypse.MovementExpression;
import au.com.ailo.zombie.apocalypse.handler.RequestHandler;
import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.Direction;
import au.com.ailo.zombie.apocalypse.types.IGrid;
import au.com.ailo.zombie.apocalypse.types.Request;
import au.com.ailo.zombie.apocalypse.types.Tuple;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.NonNull;

public class InputDispatcher {

  private InputDispatcher() {}

  private static final String REQUEST_FILE = "requestFile";

  public static Tuple<List<Coordinate>, List<Coordinate>> dispatch() {

    final Map<String, String> jvmArgs = mapJvmArgs();

    final String userRequestFile = jvmArgs.get(REQUEST_FILE);

    final ObjectMapper objectMapper = new ObjectMapper();

    final Tuple<List<Coordinate>, List<Coordinate>> result = new Tuple<>();

    try {
      final Request request = objectMapper.readValue(new File(userRequestFile), Request.class);

      final List<Direction> moves = MovementExpression.parse(request.getMoves().toUpperCase());

      final int gridSize = request.getGridSize();

      final Coordinate rootZombiePosition = request.getInitialZombiePosition();

      final Queue<Coordinate> zombieQueue = new ArrayDeque<>();

      final IGrid grid = new Grid(gridSize);

      final RequestHandler requestHandler = new RequestHandler(grid, zombieQueue);

      zombieQueue.add(rootZombiePosition);

      loadCreatures(request, grid);

      requestHandler.handle(moves);

      updateResult(result, grid);

    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }

    return result;
  }

  private static void loadCreatures(Request request, IGrid grid) {
    final List<Coordinate> creaturePositions = request.getInitialCreaturePosition();
    creaturePositions.forEach(grid::creaturefyCoordinate);
  }

  private static void updateResult(Tuple<List<Coordinate>, List<Coordinate>> result, IGrid grid) {
    result.setLeft(grid.zombieLocations());
    result.setRight(grid.creatureLocations());
  }

  private static Map<String, String> mapJvmArgs() {
    String requestFile = System.getProperty(REQUEST_FILE).trim();
    jvmArgsPreconditions(requestFile);
    return Map.of(REQUEST_FILE, requestFile);
  }

  private static void jvmArgsPreconditions(@NonNull String requestFile) {
    if (requestFile.isBlank() || requestFile.isEmpty())
      throw new IllegalArgumentException(
          "requestFile is either blank or empty. requestFile cannot be blank or zero-length string");
  }
}
