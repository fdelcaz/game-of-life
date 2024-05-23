import java.util.ArrayList;

public class Grid {

  private ArrayList<String> gridLines;

  public ArrayList<String> getGridLines() {
    return gridLines;
  }

  public void setGridLines(ArrayList<String> gridLines) {
    this.gridLines = gridLines;
  }

  public Grid(ArrayList<String> gridLines) throws Exception {
    if (gridLines.get(0).length() < 2){
      throw new Exception("Invalid grid size");
    }
    this.gridLines = gridLines;
  }

  public void nextMove() {


    ArrayList<String> nextGenGridLines = new ArrayList<>(gridLines);

    for (int x = 0; x<gridLines.size(); x++) {
      for (int y = 0; y<gridLines.size(); y++) {
        if (gridLines.get(x).charAt(y) == '*'){
          //Count number of neighbours
          int neighboursCount = 0;
          //Upper row
          int upperRowIndex = x-1;
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y - 1) ? 1 : 0);
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y) ? 1 : 0);
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y + 1) ? 1 : 0);

          //Middle row
          upperRowIndex = x;
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex , y - 1) ? 1 : 0);
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y + 1) ? 1 : 0);

          //Bottom row
          upperRowIndex = x+1;
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y-1) ? 1 : 0);
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y) ? 1 : 0);
          neighboursCount = neighboursCount + (isCellAliveAt(upperRowIndex, y+1) ? 1 : 0);

          if (neighboursCount < 2 || neighboursCount > 3 ){
            String newLine = nextGenGridLines.get(x).substring(0, y) + "."  + nextGenGridLines.get(x).substring(y+1);
            nextGenGridLines.set(x, newLine);
          }
        }
      }
    }

    setGridLines(nextGenGridLines);
  }

  private boolean isCellAliveAt(int x, int y) {
    try{
      char neighbour = gridLines.get(x).charAt(y);
      if (neighbour == '*') {
        return true;
      }
      return false;
    }catch (Exception e){ //Out of boundaries
      return false;
    }

  }

  public void print() {
    for (String line : gridLines) {
      System.out.println(line);
    }
  }
}
