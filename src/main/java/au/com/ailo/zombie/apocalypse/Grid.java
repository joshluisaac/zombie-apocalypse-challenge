package au.com.ailo.zombie.apocalypse;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.IGrid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;

public class Grid implements IGrid {

  private final Map<String, Coordinate> creatureLocations = new HashMap<>();

  private final List<Coordinate> zombieLocations = new ArrayList<>();

  private final int size;

  public Grid(int size) {
    this.size = size;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public void infectCreature(Coordinate coordinate) {
    creatureLocations.remove(DigestUtils.md5Hex(coordinate.toString()));
  }

  @Override
  public void creaturefyCoordinate(Coordinate coordinate) {
    creatureLocations.put(DigestUtils.md5Hex(coordinate.toString()), coordinate);
  }

  @Override
  public boolean hasCreature(Coordinate coordinate) {
    return creatureLocations.containsKey(DigestUtils.md5Hex(coordinate.toString()));
  }

  @Override
  public List<Coordinate> zombieLocations() {
    return this.zombieLocations;
  }

  @Override
  public List<Coordinate> creatureLocations() {
    return new ArrayList<>(this.creatureLocations.values());
  }
}
