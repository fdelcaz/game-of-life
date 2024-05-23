import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

	@Test
	public void GridShouldBeBiggerThanOne() {

    ArrayList<String> gridLines = new ArrayList<>();
    gridLines.add(".");

    Exception exception = assertThrows(Exception.class, () -> new Grid(gridLines));

    assertEquals("Invalid grid size", exception.getMessage());
	}

}
