import java.util.ArrayList;

public class Grid {

  private final ArrayList<String> gridLines;

  public Grid(ArrayList<String> gridLines) throws Exception {
    if (gridLines.getFirst().length() < 2){
      throw new Exception("Invalid grid size");
    }
    this.gridLines = gridLines;
  }
}
