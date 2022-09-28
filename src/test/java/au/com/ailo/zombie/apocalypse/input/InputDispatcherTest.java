package au.com.ailo.zombie.apocalypse.input;

import static au.com.ailo.zombie.apocalypse.types.Coordinate.create;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.Tuple;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputDispatcherTest {

  @BeforeEach
  public void beforeInputDispatcherTest() {
    System.setProperty("requestFile", "src/test/resources/requests/request.json");
  }

  @AfterEach
  void clearSystemProperties() {
    System.clearProperty("requestFile");
  }

  @Test
  void dispatch_withRequestFile_ShouldDispatchInputs() {
    assertDoesNotThrow(InputDispatcher::dispatch);
  }

  @Test
  void dispatch_withEmptyRequestFile_ShouldThrowException() {
    System.setProperty("requestFile", "");
    Throwable throwable = assertThrows(IllegalArgumentException.class, InputDispatcher::dispatch);
    assertEquals(
        "requestFile is either blank or empty. requestFile cannot be blank or zero-length string",
        throwable.getMessage());
  }

  @Test
  void dispatch_WithBlankRequestFile_ShouldThrowException() {
    System.setProperty("requestFile", "     ");
    Throwable throwable = assertThrows(IllegalArgumentException.class, InputDispatcher::dispatch);
    assertEquals(
        "requestFile is either blank or empty. requestFile cannot be blank or zero-length string",
        throwable.getMessage());
  }

  @Test
  void dispatch_WithNoRequestFile_ShouldThrowException() {
    System.setProperty("requestFile", "noFile.json");
    Throwable throwable = assertThrows(RuntimeException.class, InputDispatcher::dispatch);
    assertEquals(
        "java.io.FileNotFoundException: noFile.json (No such file or directory)",
        throwable.getMessage());
  }

  @Test
  void dispatch_ShouldDispatch_RequestFile0() {
    System.setProperty("requestFile", "src/test/resources/requests/request0.json");
    Tuple<List<Coordinate>, List<Coordinate>> result = InputDispatcher.dispatch();
    List<Coordinate> zombies = result.getLeft();
    List<Coordinate> creatures = result.getRight();
    int zombieCount = zombies.size();
    int creatureCount = creatures.size();
    assertEquals(4, zombieCount);
    assertEquals(0, creatureCount);

    assertTrue(zombies.contains(create(1, 1)));
    assertTrue(zombies.contains(create(2, 1)));
    assertTrue(zombies.contains(create(3, 2)));
    assertTrue(zombies.contains(create(3, 1)));
  }

  @Test
  void dispatch_ShouldDispatch_RequestFile1() {
    System.setProperty("requestFile", "src/test/resources/requests/request1.json");
    Tuple<List<Coordinate>, List<Coordinate>> result = InputDispatcher.dispatch();
    List<Coordinate> zombies = result.getLeft();
    List<Coordinate> creatures = result.getRight();
    int zombieCount = zombies.size();
    int creatureCount = creatures.size();
    assertEquals(4, zombieCount);
    assertEquals(0, creatureCount);

    assertTrue(zombies.contains(create(3, 0)));
    assertTrue(zombies.contains(create(2, 1)));
    assertTrue(zombies.contains(create(1, 0)));
    assertTrue(zombies.contains(create(0, 0)));
  }
}
