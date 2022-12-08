package ph.itsmewnewbie03.snakescii;

/**
 * Class for Manipulating Coordinates in the Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Coordinate implements CoordinateMethods{
    int x;
    int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(String coordinate){
        String[] coordinate_cleaned = coordinate.replaceAll("(\\(|\\))","").split(",");
        this.x = Integer.parseInt(coordinate_cleaned[0]);
        this.y = Integer.parseInt(coordinate_cleaned[1]);
    }
    @Override
    public Coordinate up() {
        return new Coordinate(this.x,this.y-1);
    }
    @Override
    public Coordinate left() {
        return new Coordinate(this.x-1,this.y);
    }
    @Override
    public Coordinate right() {
        return new Coordinate(this.x+1,this.y);
    }
    @Override
    public Coordinate down() {
        return new Coordinate(this.x,this.y+1);
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Coordinate c = (Coordinate) obj;
        return this.x == c.x && this.y == c.y;
    }
    public static Coordinate randomCoordinate() {
        int xmin = 1;
        int ymin = 1;
        int xmax = 99;
        int ymax = 21;
        int rand_x = (int) (Math.random() * (xmax - xmin + 1) + xmin);
        int rand_y = (int) (Math.random() * (ymax - ymin + 1) + ymin);
        return new Coordinate(rand_x, rand_y);
    }
    @Override
    public String toString() {
        return String.format("(%d,%d)",this.x,this.y);
    }
    
}
