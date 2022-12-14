package au.com.ailo.zombie.apocalypse.handler;

import static au.com.ailo.zombie.apocalypse.utils.Utils.printer;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.Direction;
import au.com.ailo.zombie.apocalypse.types.IGrid;
import au.com.ailo.zombie.apocalypse.types.Tuple;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestHandler {

  private final IGrid grid;

  private final Queue<Coordinate> zombieQueue;

  public void handle(List<Direction> moves) {
    while (!zombieQueue.isEmpty()) {
      Coordinate zombiePosition = zombieQueue.poll();
      String zombieId = getZombieId();
      AtomicReference<Coordinate> position = new AtomicReference<>(zombiePosition);
      moves.forEach(move -> handleMoves(move, position, zombieId));
      printer("");
      grid.zombieLocations().add(position.get());
    }
  }

  public void handleMoves(
      Direction direction, AtomicReference<Coordinate> position, String zombieId) {

    Coordinate currentPosition = position.get();

    Stream.of(handleGoRight(), handleGoLeft(), handleGoDown(), handleGoUp())
        .filter(tuple -> tuple.getLeft().test(direction))
        .findFirst()
        .orElseThrow()
        .getRight()
        .accept(currentPosition);

    position.set(currentPosition);
    logLocation(currentPosition, zombieId);

    if (grid.hasCreature(currentPosition)) {
      logInfection(currentPosition, zombieId);
      Coordinate infectedCoordinate = Coordinate.create(currentPosition);
      zombieQueue.add(infectedCoordinate);
      grid.infectCreature(currentPosition);
    }
  }

  private Tuple<Predicate<Direction>, Consumer<Coordinate>> handleGoRight() {
    Predicate<Direction> predicate = direction -> direction == Direction.RIGHT;
    Consumer<Coordinate> coordinateConsumer = this::goRight;

    return new Tuple<>(predicate, coordinateConsumer);
  }

  private Tuple<Predicate<Direction>, Consumer<Coordinate>> handleGoLeft() {
    Predicate<Direction> predicate = direction -> direction == Direction.LEFT;
    Consumer<Coordinate> coordinateConsumer = this::goLeft;

    return new Tuple<>(predicate, coordinateConsumer);
  }

  private Tuple<Predicate<Direction>, Consumer<Coordinate>> handleGoUp() {
    Predicate<Direction> predicate = direction -> direction == Direction.UP;
    Consumer<Coordinate> coordinateConsumer = this::goUp;

    return new Tuple<>(predicate, coordinateConsumer);
  }

  private Tuple<Predicate<Direction>, Consumer<Coordinate>> handleGoDown() {
    Predicate<Direction> predicate = direction -> direction == Direction.DOWN;
    Consumer<Coordinate> coordinateConsumer = this::goDown;

    return new Tuple<>(predicate, coordinateConsumer);
  }

  private static void logInfection(Coordinate currentPosition, String zombieId) {
    printer(
        String.format(
            "Zombie %s infected creature at (%d,%d)",
            zombieId, currentPosition.getX(), currentPosition.getY()));
  }

  private static void logLocation(Coordinate currentPosition, String zombieId) {
    printer(
        String.format(
            "Zombie %s moved to (%d,%d)",
            zombieId, currentPosition.getX(), currentPosition.getY()));
  }

  private void goDown(Coordinate currentPosition) {
    int nextY = currentPosition.getY() + 1;
    if (nextY > (grid.size() - 1)) nextY = 0;
    currentPosition.setY(nextY);
  }

  private void goUp(Coordinate currentPosition) {
    int nextY = currentPosition.getY() - 1;
    if (nextY < 0) nextY = (grid.size() - 1);
    currentPosition.setY(nextY);
  }

  private void goLeft(Coordinate currentPosition) {
    int nextX = currentPosition.getX() - 1;
    if (nextX < 0) nextX = (grid.size() - 1);
    currentPosition.setX(nextX);
  }

  private void goRight(Coordinate currentPosition) {
    int nextX = currentPosition.getX() + 1;
    if (nextX > (grid.size() - 1)) nextX = 0;
    currentPosition.setX(nextX);
  }

  private static String getZombieId() {
    return UUID.randomUUID().toString().split("-")[0];
  }
}
