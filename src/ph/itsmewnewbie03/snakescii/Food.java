package ph.itsmewnewbie03.snakescii;

import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating the Food in the Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Food {
    static volatile Coordinate coordinate;
    static TextGraphics textGraphics;

    public Food(Coordinate coordinate, Terminal terminal) throws IOException {
        Food.coordinate = coordinate;
        Food.textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(ANSI.DEFAULT);
        textGraphics.setForegroundColor(ANSI.RED_BRIGHT);
    }

    public static void randomSpawn() {
        Coordinate randomCoordinate = Coordinate.randomCoordinate();
        String rand_pos = Food.textGraphics.getCharacter(new TerminalPosition(randomCoordinate.x, randomCoordinate.y))
                .getCharacterString();
        if (coordinate != null) {
            textGraphics.putString(coordinate.x, coordinate.y, " ");
        }
        if (rand_pos.equals(" ")) {
            drawFood(randomCoordinate);
            coordinate = randomCoordinate;
            return;
        }
        Food.randomSpawn();
    }

    public static synchronized Coordinate getCoordinate() {
        return coordinate;
    }

    public static void drawFood() {
        if (coordinate == null)
            return;
        textGraphics.putString(coordinate.x, coordinate.y, "♥", SGR.BOLD);
    }

    public static void drawFood(Coordinate _coordinate) {
        textGraphics.putString(_coordinate.x, _coordinate.y, "♥", SGR.BOLD);
    }
}
