package ph.itsmewnewbie03.snakescii;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Class for Manipulating Player 2's Snake Movement
 * 
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class SnakeMovement1 implements Runnable {
    GameScreen gameScreen;
    Terminal terminal;
    AudioController audioController;
    int speed;
    boolean sfx_on;

    public SnakeMovement1(GameScreen gameScreen, Terminal terminal) throws IOException {
        this.gameScreen = gameScreen;
        this.terminal = terminal;
        this.audioController = new AudioController();
        this.speed = SnakeGame.gameConfig.getSpeed();
        this.sfx_on = SnakeGame.gameConfig.isSfx_on();
    }

    public void run() {
        try {
            while (!gameScreen.gameOver() && !GameScreen.forced_close) {
                if (GameScreen.isPaused()) {
                    continue;
                }
                Coordinate next = null;
                switch (GameScreen.snakeDirection1) {
                    case DOWN:
                        next = Snake1.head().down();
                        break;
                    case LEFT:
                        next = Snake1.head().left();
                        break;
                    case RIGHT:
                        next = Snake1.head().right();
                        break;
                    case UP:
                        next = Snake1.head().up();
                        break;
                    default:
                        break;
                }
                if (GameScreen.snakeDirection1 != SnakeDirection.NONE) {
                    if (gameScreen.gameOver(next, 1)) {
                        gameScreen.setGameOver(true);
                        if (sfx_on) {
                            audioController.play(SoundEffects.DIE);
                        }
                        Graphics.drawGameOver(terminal);
                        // well can't fix so im hacking hahahaha
                        Robot r = new Robot();
                        r.keyPress(KeyEvent.VK_A);
                        KeyStroke prompt = this.terminal.readInput();
                        while (prompt.getKeyType() != KeyType.Enter) {
                            prompt = this.terminal.readInput();
                        }
                        return;
                    }
                    if (next != null) {
                        if (next.equals(Food.coordinate)) {
                            if (sfx_on) {
                                audioController.play(SoundEffects.EAT);
                            }
                            Snake1.grow(next);
                            Food.randomSpawn();
                            if (GameScreen.has_obstacle) {
                                Obstacle.randomSpawn();
                            }
                        } else {
                            Snake1.move(next);
                        }
                    }
                    gameScreen.drawSnake();
                    terminal.flush();
                    Thread.sleep(300 / speed);
                }
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            try {
                FileWriter myWriter = new FileWriter("error.log");
                myWriter.write(sStackTrace);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
            try {
                Graphics.drawErrorScreen(terminal);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
