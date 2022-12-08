package ph.itsmewnewbie03.snakescii;

import java.util.Stack;

import com.googlecode.lanterna.graphics.TextGraphics;
/**
 * Class for Manipulating Player 2's Snake
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Snake1 {
    public static volatile Stack<Coordinate> snake;
    static TextGraphics textGraphics;

    public Snake1(Stack<Coordinate> snake,TextGraphics textGraphics) {
        Snake1.snake = new Stack<>();
        Snake1.snake = snake;
        Snake1.textGraphics = textGraphics;
    }
    public Snake1(Coordinate head, TextGraphics textGraphics) {
        Snake1.snake = new Stack<>();
        Snake1.snake.add(head);
        Snake1.textGraphics = textGraphics;
    }

    public static void grow(Coordinate coordinate) {
        Snake1.snake.add(coordinate);
    }

    public static void move(Coordinate coordinate) {
        Coordinate toErase = Snake1.snake.remove(0);
        Snake1.snake.add(coordinate);
        Snake1.textGraphics.putString(toErase.x, toErase.y, " ");
    }

    public static Coordinate head() {
        return Snake1.snake.get(Snake1.snake.size() - 1);
    }
    public static synchronized Stack<Coordinate> getSnake(){
        return snake;
    }
}
