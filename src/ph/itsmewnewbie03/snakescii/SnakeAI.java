package ph.itsmewnewbie03.snakescii;
import java.util.Stack;

import com.googlecode.lanterna.graphics.TextGraphics;
/**
 * Class for Manipulating the AI_Snake
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class SnakeAI {
    public static volatile Stack<Coordinate> snake;
    static TextGraphics textGraphics;

    public SnakeAI(Stack<Coordinate> snake,TextGraphics textGraphics) {
        SnakeAI.snake = new Stack<>();
        SnakeAI.snake = snake;
        SnakeAI.textGraphics = textGraphics;
    }
    public SnakeAI(Coordinate head, TextGraphics textGraphics) {
        SnakeAI.snake = new Stack<>();
        SnakeAI.snake.add(head);
        SnakeAI.textGraphics = textGraphics;
    }

    public static void grow(Coordinate coordinate) {
        SnakeAI.snake.add(coordinate);
    }

    public static void move(Coordinate coordinate) {
        Coordinate toErase = SnakeAI.snake.remove(0);
        SnakeAI.snake.add(coordinate);
        SnakeAI.textGraphics.putString(toErase.x, toErase.y, " ");
    }

    public static Coordinate head() {
        return SnakeAI.snake.get(SnakeAI.snake.size() - 1);
    }
}
