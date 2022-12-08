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
 * Class for Manipulating Player 1's Snake Movement
 * 
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class SnakeMovement implements Runnable {
    GameScreen gameScreen;
    Terminal terminal;
    AudioController audioController;
    int speed;
    boolean sfx_on;

    public SnakeMovement(GameScreen gameScreen, Terminal terminal) throws IOException {
        this.gameScreen = gameScreen;
        this.terminal = terminal;
        this.audioController = new AudioController();
        this.speed = SnakeGame.gameConfig.getSpeed();
        this.sfx_on = SnakeGame.gameConfig.isSfx_on();
        if (sfx_on) {
            audioController.play(SoundEffects.START);
        }
    }

    public void run() {
        try {
            while (!gameScreen.gameOver() && !GameScreen.forced_close) {
                if (GameScreen.isPaused()) {
                    continue;
                }
                Coordinate next = null;
                switch (GameScreen.snakeDirection) {
                    case DOWN:
                        next = Snake.head().down();
                        break;
                    case LEFT:
                        next = Snake.head().left();
                        break;
                    case RIGHT:
                        next = Snake.head().right();
                        break;
                    case UP:
                        next = Snake.head().up();
                        break;
                    default:
                        break;
                }
                if (GameScreen.snakeDirection != SnakeDirection.NONE) {
                    if (gameScreen.gameOver(next, 0)) {
                        gameScreen.setGameOver(true);
                        if (sfx_on) {
                            audioController.play(SoundEffects.DIE);
                        }
                        Graphics.drawGameOver(terminal);
                        // well can't fix so im hacking hahahaha
                        Robot r = new Robot();
                        r.keyPress(KeyEvent.VK_SPACE);
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
                            Snake.grow(next);
                            Food.randomSpawn();
                            if (GameScreen.has_obstacle) {
                                Obstacle.randomSpawn();
                            }
                        } else {
                            Snake.move(next);
                        }
                    }
                    gameScreen.drawSnake();
                    gameScreen.drawScore();
                    terminal.flush();
                    Thread.sleep(300 / speed);
                    throw new RuntimeException("THIS IS AN ERROR I MADE MY SELF TO TEST MY ERROR HANDLING xD");
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
