package au.com.ailo.zombie.apocalypse;

import static au.com.ailo.zombie.apocalypse.types.Coordinate.create;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import au.com.ailo.zombie.apocalypse.types.Coordinate;
import au.com.ailo.zombie.apocalypse.types.IGrid;
import java.util.List;
import org.junit.jupiter.api.Test;

class GridTest {

  @Test
  void infectCreature_OnCoordinates_ShouldRemoveCreature() {
    IGrid grid = new Grid(4);
    List<Coordinate> creaturePositions = List.of(create(1, 2), create(3, 3));
    loadCreatures(creaturePositions, grid);

    grid.infectCreature(create(1, 2));

    assertEquals(1, grid.creatureLocations().size());
  }

  @Test
  void creaturefyCoordinate_WithCoordinates_ShouldPlaceCreatureAtPosition() {
    IGrid grid = new Grid(4);
    List<Coordinate> creaturePositions = List.of(create(1, 2), create(3, 3));

    loadCreatures(creaturePositions, grid);

    assertEquals(2, grid.creatureLocations().size());
  }

  @Test
  void hasCreature_WithCoordinates_ShouldTestIfCreatureExists() {
    IGrid grid = new Grid(4);
    List<Coordinate> creaturePositions = List.of(create(1, 2), create(3, 0), create(3, 3));
    loadCreatures(creaturePositions, grid);

    assertTrue(grid.hasCreature(create(3, 0)));
  }

  @Test
  void creatureLocations_ShouldReturnAllCreatures() {
    IGrid grid = new Grid(4);
    List<Coordinate> creaturePositions =
        List.of(create(1, 2), create(2, 2), create(1, 0), create(3, 0), create(3, 3));

    loadCreatures(creaturePositions, grid);

    assertEquals(5, grid.creatureLocations().size());
  }

  private static void loadCreatures(List<Coordinate> creaturePositions, IGrid grid) {
    creaturePositions.forEach(grid::creaturefyCoordinate);
  }
}
