/*
 * Author:  Ligayao, Salvador, 1
 * Section: T209
 * Subject: I121
 */
package ph.itsmewnewbie03.snakescii;
/**
 * Class for Starting The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Main {
    WindowMonitor windowMonitor;

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.initializeGame();
    }
}