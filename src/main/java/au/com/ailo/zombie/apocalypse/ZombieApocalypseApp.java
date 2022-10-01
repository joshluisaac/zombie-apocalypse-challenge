package au.com.ailo.zombie.apocalypse;

import static au.com.ailo.zombie.apocalypse.utils.Utils.printResult;
import static au.com.ailo.zombie.apocalypse.utils.Utils.printer;
import static au.com.ailo.zombie.apocalypse.utils.Utils.toJson;

import au.com.ailo.zombie.apocalypse.input.InputDispatcher;
import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.Response;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ZombieApocalypseApp {

  public static void main(String[] args) throws IOException {

    Response result = InputDispatcher.dispatch();

    List<Coordinate> zombies = result.getZombieFinalPositions();

    List<Coordinate> creatures = result.getCreatureFinalPositions();

    String zombieResult = !zombies.isEmpty() ? printResult(zombies) : "none";

    String creatureResult = !creatures.isEmpty() ? printResult(creatures) : "none";

    printer(String.format("zombies’ positions: %n%s", zombieResult));

    printer(String.format("creatures’ positions: %n%s", creatureResult));

    printer("");

    printer("Printing JSON formatted result");

    toJson(new File("response.json"), result);

    printer(toJson(result));
  }
}
