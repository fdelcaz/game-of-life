import java.util.ArrayList;
import java.util.Scanner;

public class Grid {

  private static final int MAX_TURNS = 5;


  public static void main (String[] args) throws Exception {
    ArrayList<String> initialGridLines = new ArrayList<>();
    initialGridLines.add(".*.");
    initialGridLines.add("*.*");
    initialGridLines.add(".**");
    Grid initialGrid = new Grid(initialGridLines);

    Scanner input = new Scanner(System.in);
    int i = 0;
    while (i<10){
      initialGrid.print();
      initialGrid.nextMove();
      input.nextLine();
      i++;
    }
  }

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
        getNextGenLines(x, y, nextGenGridLines);
      }
    }

    setGridLines(nextGenGridLines);
  }

  private void getNextGenLines(int x, int y, ArrayList<String> nextGenGridLines) {
    int neighboursCount = 0;
    neighboursCount = getNeighboursCount(x, y, neighboursCount);

    if (gridLines.get(x).charAt(y) == '*'){
      if (neighboursCount < 2 || neighboursCount > 3 ){
        String newLine = nextGenGridLines.get(x).substring(0, y) + "."  + nextGenGridLines.get(x).substring(y +1);
        nextGenGridLines.set(x, newLine);
      }
    }else{
      if (neighboursCount == 3 ){
        String newLine = nextGenGridLines.get(x).substring(0, y) + "*"  + nextGenGridLines.get(x).substring(y +1);
        nextGenGridLines.set(x, newLine);
      }
    }
  }

  private int getNeighboursCount(int x, int y, int neighboursCount) {

    for(int i = x-1; i<=x+1; i++){
      for(int j = y-1; j<=y+1; j++){
        if(!(i == x && j == y)){
          neighboursCount = neighboursCount + (isCellAliveAt(i, j) ? 1 : 0);
          if(neighboursCount >3){
            break;
          }
        }
      }
    }
    return neighboursCount;
  }

  private boolean isCellAliveAt(int x, int y) {
    try{
      char neighbour = gridLines.get(x).charAt(y);
      return neighbour == '*';
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
