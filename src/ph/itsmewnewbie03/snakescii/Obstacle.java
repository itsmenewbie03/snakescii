package ph.itsmewnewbie03.snakescii;
import java.io.IOException;
import java.util.Stack;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating the Obstacles of The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Obstacle {
    static Stack<Coordinate> coordinates;
    static TextGraphics textGraphics;

    public Obstacle(Terminal terminal) throws IOException {
        Obstacle.coordinates = new Stack<>();
        Obstacle.textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(ANSI.DEFAULT);
        textGraphics.setForegroundColor(ANSI.BLUE_BRIGHT);
    }

    public static void randomSpawn() {
        Coordinate randomCoordinate = Coordinate.randomCoordinate();
        String rand_pos = Obstacle.textGraphics.getCharacter(new TerminalPosition(randomCoordinate.x, randomCoordinate.y))
                .getCharacterString();
        if (rand_pos.equals(" ")) {
            coordinates.add(randomCoordinate);
            drawObstacles();
            return;
        }
    }
    public static void drawObstacles(){
        for (Coordinate coordinate : coordinates) {
            textGraphics.putString(coordinate.x, coordinate.y, "卍",SGR.BOLD);
            
        }
    }
    public static boolean collides(Coordinate coordinate){
        return coordinates.contains(coordinate);
    }
}
