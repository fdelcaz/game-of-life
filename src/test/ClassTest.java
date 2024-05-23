import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

	@Test
	public void gridShouldBeBiggerThanOne() {

    ArrayList<String> gridLines = new ArrayList<>();
    gridLines.add(".");

    Exception exception = assertThrows(Exception.class, () -> new Grid(gridLines));

    assertEquals("Invalid grid size", exception.getMessage());
	}

  @Test
  public void cannotCreateLifeIfThereIsNoPreviousAliveCells() throws Exception {

    ArrayList<String> gridLines = new ArrayList<>();
    gridLines.add("..");
    gridLines.add("..");
    Grid initialGrid = new Grid(gridLines);
    initialGrid.nextMove();

    Grid expectedGrid = new Grid(gridLines);

    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void checkCellDiesFromUnderPopulation() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("*.");
    initialGridLines.add("..");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("..");
    expectedGridLines.add("..");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Disabled("Disabled as you cannot have more than 3 neighbours in a 2x2 grid")
  @Test
  public void checkCellDiesFromOverPopulation() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("**");
    initialGridLines.add("**");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add(".*");
    expectedGridLines.add("**");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Disabled("Disabled as this is too complex. I prefer to start considering a few alive cells instead of a few dead ones")
  @Test
  public void checkCellDiesFromOverPopulationInThreeByThreeGrid() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("***");
    initialGridLines.add("***");
    initialGridLines.add("***");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("..*");
    expectedGridLines.add("*.*");
    expectedGridLines.add("*.*");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void cannotHaveAliveCellsIfThereWereNoAliveOnes() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("...");
    initialGridLines.add("...");
    initialGridLines.add("...");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("...");
    expectedGridLines.add("...");
    expectedGridLines.add("...");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void aSingleAliveCellWilLDie() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("...");
    initialGridLines.add(".*.");
    initialGridLines.add("...");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("...");
    expectedGridLines.add("...");
    expectedGridLines.add("...");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void aSingleAliveCellWithTwoNeighboursSurvives() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("...");
    initialGridLines.add("***");
    initialGridLines.add("...");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("...");
    expectedGridLines.add(".*.");
    expectedGridLines.add("...");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void aCellWithMoreThanThreeNeighboursDies() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add(".*.");
    initialGridLines.add("***");
    initialGridLines.add(".*.");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add(".*.");
    expectedGridLines.add("*.*");
    expectedGridLines.add(".*.");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void aDeadCellWithThreeAliveNeighboursBecomesAlive() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add(".*.");
    initialGridLines.add("*.*");
    initialGridLines.add("...");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add(".*.");
    expectedGridLines.add(".*.");
    expectedGridLines.add("...");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }
}
