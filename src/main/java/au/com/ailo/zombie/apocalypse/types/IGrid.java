package au.com.ailo.zombie.apocalypse.types;

import java.util.List;

public interface IGrid {

  /**
   * Returns the size of the grid
   *
   * @return
   */
  int size();

  /**
   * Takes a coordinate and zombifies it. That is infects the creature
   *
   * @param coordinate
   */
  void infectCreature(Coordinate coordinate);

  /**
   * Takes a coordinate and add a creature to it
   *
   * @param coordinate
   */
  void creaturefyCoordinate(Coordinate coordinate);

  /**
   * Checks if a coordinate has creature in it
   *
   * @param coordinate
   * @return
   */
  boolean hasCreature(Coordinate coordinate);

  /**
   * Returns the list of zombie positions
   *
   * @return
   */
  List<Coordinate> zombieLocations();

  /**
   * Returns the list of creature positions
   *
   * @return
   */
  List<Coordinate> creatureLocations();
}
