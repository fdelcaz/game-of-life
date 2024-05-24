import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

  @Test
  public void checkCellDiesFromOverPopulation() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("**");
    initialGridLines.add("**");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("**");
    expectedGridLines.add("**");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  @Test
  public void checkCellDiesFromOverPopulationInThreeByThreeGrid() throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add("***");
    initialGridLines.add("***");
    initialGridLines.add("***");
    Grid initialGrid = new Grid(initialGridLines);

    ArrayList<String> expectedGridLines = new ArrayList<>();
    expectedGridLines.add("*.*");
    expectedGridLines.add("...");
    expectedGridLines.add("*.*");
    Grid expectedGrid = new Grid(expectedGridLines);

    initialGrid.nextMove();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }

  static Stream<TestData> provideTestData() {
    return Stream.of(
      new TestData("cannotHaveAliveCellsIfThereWereNoAliveOnes", List.of("...", "...", "..."), List.of("...", "...", "...")),
      new TestData("aSingleAliveCellWilLDie", List.of("...", ".*.", "..."), List.of("...", "...", "...")),
      new TestData("aSingleAliveCellWithTwoNeighboursSurvives", List.of("...", "***", "..."), List.of(".*.", ".*.", ".*.")),
      new TestData("aCellWithMoreThanThreeNeighboursDies", List.of(".*.", "***", ".*."), List.of("***", "*.*", "***")),
      new TestData("aDeadCellWithThreeAliveNeighboursBecomesAlive", List.of(".*.", "*.*", "..."), List.of(".*.", ".*.", "..."))
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("provideTestData")
  @DisplayName("Test Grid Configuration")
  public void testGrid(TestData data) throws Exception {
    Grid initialGrid = new Grid(new ArrayList<>(data.initialGridLines));
    initialGrid.nextMove();
    Grid expectedGrid = new Grid(new ArrayList<>(data.expectedGridLines));
    assertEquals(expectedGrid.getGridLines(), initialGrid.getGridLines());
  }

  static class TestData {
    String testName;
    List<String> initialGridLines;
    List<String> expectedGridLines;

    TestData(String testName, List<String> initialGridLines, List<String> expectedGridLines) {
      this.testName = testName;
      this.initialGridLines = initialGridLines;
      this.expectedGridLines = expectedGridLines;
    }

    @Override
    public String toString() {
      return testName;
    }
  }
}
