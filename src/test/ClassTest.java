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
    initialGrid = initialGrid.NextMove();

    Grid expectedGrid = new Grid(gridLines);

    initialGrid.print();
    expectedGrid.print();
    assertEquals(expectedGrid.getGridLines(),initialGrid.getGridLines());
  }
}
