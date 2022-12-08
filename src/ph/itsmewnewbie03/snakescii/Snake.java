package ph.itsmewnewbie03.snakescii;

import java.util.Stack;

import com.googlecode.lanterna.graphics.TextGraphics;
/**
 * Class for Manipulating Player 1's Snake
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Snake {
    public static Stack<Coordinate> snake;
    static TextGraphics textGraphics;

    public Snake(Stack<Coordinate> snake,TextGraphics textGraphics) {
        Snake.snake = new Stack<>();
        Snake.snake = snake;
        Snake.textGraphics = textGraphics;
    }
    public Snake(Coordinate head, TextGraphics textGraphics) {
        Snake.snake = new Stack<>();
        Snake.snake.add(head);
        Snake.textGraphics = textGraphics;
    }

    public static void grow(Coordinate coordinate) {
        Snake.snake.add(coordinate);
    }

    public static void move(Coordinate coordinate) {
        Coordinate toErase = Snake.snake.remove(0);
        Snake.snake.add(coordinate);
        Snake.textGraphics.putString(toErase.x, toErase.y, " ");
    }

    public static Coordinate head() {
        return Snake.snake.get(Snake.snake.size() - 1);
    }

    public static synchronized Stack<Coordinate> getSnake() {
        return snake;
    }

}
