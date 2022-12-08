package ph.itsmewnewbie03.snakescii;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Class for Manipulating AI Snake Movement
 * 
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class AISnakeMovement implements Runnable {
    GameScreen gameScreen;
    Terminal terminal;
    AudioController audioController;
    private final int max_rethink = 5;
    private ArrayList<SnakeDirection> badMoves = new ArrayList<>();
    int think_count = 0;
    int speed;
    boolean sfx_on;

    public AISnakeMovement(GameScreen gameScreen, Terminal terminal) throws IOException {
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
                GameScreen.aiSnakeDirection = generateMove(SnakeDirection.NONE);
                Coordinate next = null;
                switch (GameScreen.aiSnakeDirection) {
                    case DOWN:
                        next = SnakeAI.head().down();
                        break;
                    case LEFT:
                        next = SnakeAI.head().left();
                        break;
                    case RIGHT:
                        next = SnakeAI.head().right();
                        break;
                    case UP:
                        next = SnakeAI.head().up();
                        break;
                    default:
                        break;
                }
                if (GameScreen.aiSnakeDirection != SnakeDirection.NONE) {
                    if (gameScreen.gameOver(next, 1)) {
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
                            SnakeAI.grow(next);
                            Food.randomSpawn();
                            if (GameScreen.has_obstacle) {
                                Obstacle.randomSpawn();
                            }
                        } else {
                            SnakeAI.move(next);
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

    public SnakeDirection generateMove(SnakeDirection badMove) {
        if (GameScreen.snakeDirection == SnakeDirection.NONE) {
            return SnakeDirection.NONE;
        }
        // this would be fun let's see where can my skills take me :)
        // make my ai avoid the walls
        // here i go, im writing comments coz my brain is about to commit suicide
        Coordinate head = SnakeAI.head();
        Coordinate foodCoordinate = Food.getCoordinate();
        // not yet moving, gotta see where the food is xD
        // negative means the food right, positive if otherwise
        int xdiff = head.x - foodCoordinate.x;
        // negative means the food is below, positive if otherwise
        int ydiff = head.y - foodCoordinate.y;

        SnakeDirection move = SnakeDirection.NONE;

        if (badMove == SnakeDirection.NONE) {
            if (xdiff == 0) {
                move = ydiff > 0 ? SnakeDirection.UP : SnakeDirection.DOWN;
            } else if (ydiff == 0) {
                move = xdiff > 0 ? SnakeDirection.LEFT : SnakeDirection.RIGHT;
            } else {
                // there is no bad move it will consider which is closer? idk if im doing it
                // right hahaha
                move = Math.abs(xdiff) > Math.abs(ydiff) ? ydiff > 0 ? SnakeDirection.UP : SnakeDirection.DOWN
                        : xdiff > 0 ? SnakeDirection.LEFT : SnakeDirection.RIGHT;
            }
        }
        // move y since x move is bad
        if (badMove == SnakeDirection.RIGHT || badMove == SnakeDirection.LEFT) {
            move = Math.random() < 0.5 ? SnakeDirection.UP : SnakeDirection.DOWN;
            if (badMoves.contains(move)) {
                move = getOpposite(move);
            }
            // return move;
        }
        // move x since y move is bad
        if (badMove == SnakeDirection.UP || badMove == SnakeDirection.DOWN) {
            move = Math.random() < 0.5 ? SnakeDirection.LEFT : SnakeDirection.RIGHT;
            if (badMoves.contains(move)) {
                move = getOpposite(move);
            }
            // return move;
        }

        if (isGoodMove(move)) {
            badMove = SnakeDirection.NONE;
            badMoves.clear();
            think_count = 0;
            return move;
        }
        think_count++;
        // gonna give player a chance
        if (think_count == max_rethink) {
            System.out.println("");
            return move;
        }
        badMoves.add(move);
        return generateMove(move);
    }

    /*
     * create algorithm to validate a move based on coordinate
     * gotta crush them with my AI snake hahaha
     */

    public boolean isGoodMove(SnakeDirection nextDir) {
        Coordinate next = nextCoordinate(nextDir);
        if (hasEnemyOnWay(next)) {
            return false;
        }
        if (bitesSelf(next)) {
            return false;
        }
        String nextChar = gameScreen.textGraphics.getCharacter(next.x, next.y).getCharacterString();
        if (!nextChar.trim().isEmpty() && nextChar != "♥") {
            return false;
        }
        return true;
    }

    public boolean hasEnemyOnWay(Coordinate ahead) {
        return Snake.getSnake().contains(ahead);
    }

    public boolean bitesSelf(Coordinate next) {
        return SnakeAI.snake.contains(next);
    }

    public Coordinate nextCoordinate(SnakeDirection s) {
        switch (s) {
            case DOWN:
                return SnakeAI.head().down();
            case LEFT:
                return SnakeAI.head().left();
            case NONE:
                return null;
            case RIGHT:
                return SnakeAI.head().right();
            case UP:
                return SnakeAI.head().up();
            default:
                return null;

        }
    }

    public SnakeDirection getOpposite(SnakeDirection s) {
        switch (s) {
            case DOWN:
                return SnakeDirection.UP;
            case LEFT:
                return SnakeDirection.RIGHT;
            case NONE:
                return SnakeDirection.NONE;
            case RIGHT:
                return SnakeDirection.LEFT;
            case UP:
                return SnakeDirection.DOWN;
            default:
                return SnakeDirection.NONE;
        }
    }
}
