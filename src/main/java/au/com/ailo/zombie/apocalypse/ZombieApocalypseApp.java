package au.com.ailo.zombie.apocalypse;

import static au.com.ailo.zombie.apocalypse.utils.Utils.printResult;

import au.com.ailo.zombie.apocalypse.input.InputDispatcher;
import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.Tuple;
import java.util.List;

public class ZombieApocalypseApp {

  public static void main(String[] args) {

    Tuple<List<Coordinate>, List<Coordinate>> result = InputDispatcher.dispatch();

    List<Coordinate> zombies = result.getLeft();
    List<Coordinate> creatures = result.getRight();

    String zombieResult = !zombies.isEmpty() ? printResult(zombies) : "none";
    String creatureResult = !creatures.isEmpty() ? printResult(creatures) : "none";

    System.out.println("zombies’ positions: \n" + zombieResult);
    System.out.println("creatures’ positions: \n" + creatureResult);
  }
}
