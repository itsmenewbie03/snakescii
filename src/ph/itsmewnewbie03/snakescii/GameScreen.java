package ph.itsmewnewbie03.snakescii;

import java.io.File;
import java.io.IOException;

import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating The Game Screen 
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameScreen implements KeyListen {
    SnakeGame snakeGame;
    Terminal terminal;
    Snake snake;
    Snake1 snake1;
    SnakeAI aiSnake;
    TextGraphics textGraphics;
    TextGraphics textGraphics2;
    TextGraphics textGraphics3;
    SnakeMovement snakeMovement;
    SnakeMovement1 snakeMovement1;
    AISnakeMovement aiSnakeMovement;
    AudioController audioController;
    Food food;
    Obstacle obstacle;
    Thread snakeMovementThread;
    Thread snakeMovementThread1;
    Thread aiSnakeMovementThread;
    public static Player winner;
    public static GameOverCause cause;
    public static GameModes gameMode;
    protected static SnakeDirection snakeDirection;
    protected static SnakeDirection snakeDirection1;
    protected static SnakeDirection aiSnakeDirection;
    public volatile boolean gameOver;
    public static boolean forced_close;
    public static volatile boolean paused = false;
    public static boolean savedGameLoaded = false;
    boolean bg_on;
    static boolean has_obstacle;

    public synchronized boolean gameOver() {
        return gameOver;
    }

    public synchronized void setGameOver(boolean b) {
        this.gameOver = b;
    }

    public static synchronized boolean isPaused() {
        return paused;
    }

    public static synchronized void setTerminated(boolean b) {
        GameScreen.forced_close = true;
    }

    public GameScreen(SnakeGame snakeGame, Terminal terminal, GameModes gameMode) throws IOException {
        this.snakeGame = snakeGame;
        this.terminal = terminal;
        this.textGraphics = terminal.newTextGraphics();
        this.textGraphics2 = terminal.newTextGraphics();
        this.textGraphics3 = terminal.newTextGraphics();
        GameScreen.gameMode = gameMode;
        this.food = new Food(null, terminal);
        this.obstacle = new Obstacle(terminal);
        this.bg_on = SnakeGame.gameConfig.isBg_on();
        GameScreen.has_obstacle = SnakeGame.gameConfig.has_obstacle();
        savedGameLoaded = false;
    }

    public void draw() throws Exception {
        String[] savedGameData = GameState.load();
        if (savedGameData != null) {
            // lets try to parse the saved data and load it
            // if the modes matches
            if (savedGameData[0].equals(GameScreen.gameMode.toString())) {
                drawConfirmLoad();
                if (confirm()) {
                    switch (GameScreen.gameMode) {
                        case CLASSIC:
                            snake = new Snake(GameState.buildStackFromString(savedGameData[1]), textGraphics);
                            food = new Food(
                                    new Coordinate(savedGameData[3].substring(1, savedGameData[3].length() - 1)),
                                    terminal);
                            if (savedGameData[4] != "[NULL]") {
                                Obstacle.coordinates = GameState.buildStackFromString(savedGameData[4]);
                            }
                            break;
                        case PVC:
                            snake = new Snake(GameState.buildStackFromString(savedGameData[1]), textGraphics);
                            aiSnake = new SnakeAI(GameState.buildStackFromString(savedGameData[2]), textGraphics);
                            food = new Food(
                                    new Coordinate(savedGameData[3].substring(1, savedGameData[3].length() - 1)),
                                    terminal);
                            if (savedGameData[4] != "[NULL]") {
                                Obstacle.coordinates = GameState.buildStackFromString(savedGameData[4]);
                            }
                            break;
                        case PVP:
                            snake = new Snake(GameState.buildStackFromString(savedGameData[1]), textGraphics);
                            snake1 = new Snake1(GameState.buildStackFromString(savedGameData[2]), textGraphics);
                            food = new Food(
                                    new Coordinate(savedGameData[3].substring(1, savedGameData[3].length() - 1)),
                                    terminal);
                            if (savedGameData[4] != "[NULL]") {
                                Obstacle.coordinates = GameState.buildStackFromString(savedGameData[4]);
                            }
                            break;
                        default:
                            break;

                    }
                    savedGameLoaded = true;
                }
                clearConfirmLoad();
            }
        }
        if (bg_on) {
            if (snakeGame.audio.playing) {
                snakeGame.audio.stop();
            }
            audioController = new AudioController();
            audioController.play(SoundEffects.GAME_BG);
        }
        setGameOver(false);
        drawWalls();
        switch (gameMode) {
            case CLASSIC:
                snakeDirection = SnakeDirection.NONE;
                if (!savedGameLoaded) {
                    snake = new Snake(Coordinate.randomCoordinate(), textGraphics);
                }
                snakeMovement = new SnakeMovement(this, terminal);
                snakeMovementThread = new Thread(snakeMovement);
                snakeMovementThread.start();
                break;
            case PVP:
                snakeDirection = SnakeDirection.NONE;
                snakeDirection1 = SnakeDirection.NONE;
                if (!savedGameLoaded) {
                    snake = new Snake(Coordinate.randomCoordinate(), textGraphics);
                    snake1 = new Snake1(Coordinate.randomCoordinate(), textGraphics);
                }
                snakeMovement = new SnakeMovement(this, terminal);
                snakeMovement1 = new SnakeMovement1(this, terminal);
                snakeMovementThread = new Thread(snakeMovement);
                snakeMovementThread1 = new Thread(snakeMovement1);
                snakeMovementThread.start();
                snakeMovementThread1.start();
                break;
            case PVC:
                snakeDirection = SnakeDirection.NONE;
                aiSnakeDirection = SnakeDirection.NONE;
                if (!savedGameLoaded) {
                    snake = new Snake(Coordinate.randomCoordinate(), textGraphics);
                    aiSnake = new SnakeAI(Coordinate.randomCoordinate(), textGraphics);
                }
                snakeMovement = new SnakeMovement(this, terminal);
                aiSnakeMovement = new AISnakeMovement(this, terminal);
                snakeMovementThread = new Thread(snakeMovement);
                aiSnakeMovementThread = new Thread(aiSnakeMovement);
                snakeMovementThread.start();
                aiSnakeMovementThread.start();
                break;
            default:
                break;
        }
        drawSnake();
        drawScore();
        if (savedGameLoaded)
            Food.drawFood();
        else
            Food.randomSpawn();
        if (Obstacle.coordinates.size() > 0) {
            Obstacle.drawObstacles();
        }
        terminal.flush();
        setKeyPressListener();
        // GETS CALLED AFTER GAMES ENDS
        if (audioController != null) {
            audioController.stop();
        }
        snakeMovementThread.join();
        if (gameMode == GameModes.PVP) {
            snakeMovementThread1.join();
        }
        if (gameMode == GameModes.PVC) {
            aiSnakeMovementThread.join();
        }
        Graphics.centeredTextH(terminal, ANSI.RED_BRIGHT, 17, "PLEASE WAIT FOR A WHILE.");
        terminal.flush();
        Thread.sleep(1000);
        snakeGame.drawMainScreen();
    }

    public void drawWalls() throws IOException {
        terminal.clearScreen();
        textGraphics.setForegroundColor(ANSI.CYAN);
        textGraphics.setBackgroundColor(ANSI.DEFAULT);
        textGraphics2.setBackgroundColor(ANSI.DEFAULT);
        textGraphics2.setForegroundColor(ANSI.CYAN);
        textGraphics3.setBackgroundColor(ANSI.DEFAULT);
        textGraphics3.setForegroundColor(ANSI.MAGENTA);
        int rows = terminal.getTerminalSize().getRows();
        int cols = terminal.getTerminalSize().getColumns();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int lrow = rows - 1;
                int lcol = cols - 1;
                if (row == 0 || row == lrow || row == lrow - 2) {
                    String px = "═";
                    if (col == 0) {
                        if (row != lrow - 2) {
                            px = row != lrow ? "╔" : "╚";
                        } else {
                            px = "╠";
                        }
                    }
                    if (col == lcol) {
                        if (row != lrow - 2) {
                            px = row != lrow ? "╗" : "╝";
                        } else {
                            px = "╣";
                        }
                    }
                    textGraphics2.putString(col, row, px);
                } else {
                    if (col == 0 || col == cols - 1) {
                        textGraphics2.putString(col, row, "║");
                    }
                }
            }
        }
    }

    public void drawScore() throws IOException {
        switch (gameMode) {
            case CLASSIC:
                Graphics.centeredTextH(terminal, ANSI.MAGENTA_BRIGHT, 23,
                        String.format("Your Score: %d", Snake.snake.size() - 1));
                break;
            case PVP:
                Graphics.coloredText(terminal, ANSI.MAGENTA_BRIGHT, 23, 3,
                        String.format("Players 1's Score: %d", Snake.snake.size() - 1));
                String p2_score = String.format("Players 2's Score: %d", Snake1.snake.size() - 1);
                Graphics.coloredText(terminal, ANSI.MAGENTA_BRIGHT, 23, 100 - (p2_score.length() + 5), p2_score);
                break;
            case PVC:
                Graphics.coloredText(terminal, ANSI.MAGENTA_BRIGHT, 23, 3,
                        String.format("Players 1's Score: %d", Snake.snake.size() - 1));
                String ai_score = String.format("AI's Score: %d", SnakeAI.snake.size() - 1);
                Graphics.coloredText(terminal, ANSI.MAGENTA_BRIGHT, 23, 100 - (ai_score.length() + 5), ai_score);
                break;
            default:
                break;
        }
    }

    public void drawSnake() throws IOException {
        switch (gameMode) {
            case CLASSIC:
                for (Coordinate coordinate : Snake.snake) {
                    textGraphics2.putString(coordinate.x, coordinate.y, "۝");
                }
                break;
            case PVP:
                try {
                    for (Coordinate coordinate : Snake.snake) {
                        textGraphics2.putString(coordinate.x, coordinate.y, "۝");
                    }
                    for (Coordinate coordinate : Snake1.getSnake()) {
                        textGraphics3.putString(coordinate.x, coordinate.y, "۝");
                    }
                } catch (Exception e) {
                }

                break;
            case PVC:
                try {
                    for (Coordinate coordinate : Snake.snake) {
                        textGraphics2.putString(coordinate.x, coordinate.y, "۝");
                    }
                    for (Coordinate coordinate : SnakeAI.snake) {
                        textGraphics3.putString(coordinate.x, coordinate.y, "۝");
                    }
                } catch (Exception e) {
                }
                break;
        }
        Obstacle.drawObstacles();
    }

    public void drawConfirmLoad() throws Exception {
        int rows = terminal.getTerminalSize().getRows();
        int cols = terminal.getTerminalSize().getColumns();
        int center_x = cols / 2 - 35 / 2;
        int center_y = rows / 2 - 10 / 2;
        final String[] loadConfirmBanner = {
                "                                   ",
                "                                   ",
                "    ╔═════════════════════════╗    ",
                "    ║                         ║    ",
                "    ║   LOAD PREVIOUS GAME?   ║    ",
                "    ║           Y/N           ║    ",
                "    ║                         ║    ",
                "    ╚═════════════════════════╝    ",
                "                                   ",
                "                                   ",
        };
        for (int i = 0; i < loadConfirmBanner.length; i++) {
            Graphics.coloredText(terminal, ANSI.CYAN_BRIGHT, center_y + i, center_x, loadConfirmBanner[i]);
        }
    }

    public void clearConfirmLoad() throws Exception {
        int rows = terminal.getTerminalSize().getRows();
        int cols = terminal.getTerminalSize().getColumns();
        int center_x = cols / 2 - 35 / 2;
        int center_y = rows / 2 - 10 / 2;
        for (int i = 0; i < 10; i++) {
            textGraphics.putString(center_x, center_y + i, " ".repeat(27));
        }
    }

    public boolean confirm() throws Exception {
        KeyStroke keyStroke = terminal.readInput();
        if (keyStroke.getKeyType() == KeyType.Character) {
            switch (Character.toLowerCase(keyStroke.getCharacter())) {
                case 'y':
                    return true;
                case 'n':
                    return false;
                default:
                    break;
            }
        }
        return confirm();
    }

    public void drawPausedBanner() throws Exception {
        int rows = terminal.getTerminalSize().getRows();
        int cols = terminal.getTerminalSize().getColumns();
        int center_x = cols / 2 - 26 / 2;
        int center_y = rows / 2 - 5 / 2;
        final String[] txtGraphics = {
                "╔════════════════════════╗",
                "║                        ║",
                "║         PAUSED         ║",
                "║                        ║",
                "╚════════════════════════╝"
        };
        for (int i = 0; i < txtGraphics.length; i++) {
            textGraphics.putString(center_x, center_y + i, txtGraphics[i]);
        }
    }

    public void clearPausedBanner() throws Exception {
        int rows = terminal.getTerminalSize().getRows();
        int cols = terminal.getTerminalSize().getColumns();
        int center_x = cols / 2 - 26 / 2;
        int center_y = rows / 2 - 5 / 2;
        for (int i = 0; i < 5; i++) {
            textGraphics.putString(center_x, center_y + i, " ".repeat(26));
        }
    }

    public boolean gameOver(Coordinate next) {
        int xmin = 1;
        int ymin = 1;
        int xmax = 99;
        int ymax = 21;
        // snake crashed at walls
        if (next.x < xmin || next.x > xmax || next.y < ymin || next.y > ymax) {
            cause = GameOverCause.crashed_on_a_wall;
            return true;
        }
        // check if crash on obstacle
        if (Obstacle.collides(next)) {
            cause = GameOverCause.hit_an_obstacle;
            return true;
        }
        // check if snake bit itself
        int head_index = Snake.getSnake().indexOf(next);
        if (head_index != -1 && head_index != Snake.getSnake().size() - 1) {
            cause = GameOverCause.bit_itself;
            return true;
        }

        return false;
    }

    public boolean gameOver(Coordinate next, int id) {
        // null safety hahahaha
        if (next == null) {
            return false;
        }
        int xmin = 1;
        int ymin = 1;
        int xmax = 99;
        int ymax = 21;
        // snake crashed at walls
        if (next.x < xmin || next.x > xmax || next.y < ymin || next.y > ymax) {
            winner = id == 1 ? Player.Player_1 : gameMode == GameModes.PVC ? Player.Computer : Player.Player_2;
            cause = GameOverCause.crashed_on_a_wall;
            return true;
        }
        // snake hit an obstacle
        if (Obstacle.collides(next)) {
            winner = id == 1 ? Player.Player_1 : gameMode == GameModes.PVC ? Player.Computer : Player.Player_2;
            cause = GameOverCause.hit_an_obstacle;
            return true;
        }
        // check if snake bit itself
        switch (gameMode) {
            case CLASSIC:
                // using default check if mode is classic
                return gameOver(next);
            case PVP:
                if (id == 0) {
                    int head_index = Snake.getSnake().indexOf(next);
                    // snake1 bit itself
                    if (head_index != -1 && head_index != Snake.getSnake().size() - 1) {
                        winner = Player.Player_2;
                        cause = GameOverCause.bit_itself;
                        return true;
                    }
                    int head_index1 = Snake1.getSnake().indexOf(next);
                    // snake1 hit enemy
                    if (head_index1 != -1) {
                        winner = Player.Player_2;
                        cause = GameOverCause.crashed_into_the_enemy;
                        System.out.println("");
                        return true;
                    }
                }
                if (id == 1) {
                    int head_index = Snake1.getSnake().indexOf(next);
                    // snake2 bit itself
                    if (head_index != -1 && head_index != Snake1.getSnake().size() - 1) {
                        winner = Player.Player_1;
                        cause = GameOverCause.bit_itself;
                        return true;
                    }
                    int head_index1 = Snake.getSnake().indexOf(next);
                    // snake2 hit enemy
                    if (head_index1 != -1) {
                        System.out.println("");
                        winner = Player.Player_1;
                        cause = GameOverCause.crashed_into_the_enemy;
                        return true;
                    }
                }
                break;
            case PVC:
                if (id == 0) {
                    int head_index = Snake.getSnake().indexOf(next);
                    // snake1 bit itself
                    if (head_index != -1 && head_index != Snake.getSnake().size() - 1) {
                        winner = Player.Computer;
                        cause = GameOverCause.bit_itself;
                        return true;
                    }
                    int head_index1 = SnakeAI.snake.indexOf(next);
                    // snake1 hit aisnake
                    if (head_index1 != -1) {
                        winner = Player.Computer;
                        cause = GameOverCause.crashed_into_the_enemy;
                        System.out.println("");
                        return true;
                    }
                }
                if (id == 1) {
                    int head_index = SnakeAI.snake.indexOf(next);
                    // aisnake bit itself
                    if (head_index != -1 && head_index != SnakeAI.snake.size() - 1) {
                        winner = Player.Player_1;
                        cause = GameOverCause.bit_itself;
                        return true;
                    }
                    int head_index1 = Snake.getSnake().indexOf(next);
                    // aisnake hit enemy
                    if (head_index1 != -1) {
                        winner = Player.Player_1;
                        cause = GameOverCause.crashed_into_the_enemy;
                        System.out.println("");
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void setKeyPressListener() throws Exception {
        if (this.terminal != null) {
            if (gameOver) {
                System.out.println("");
                return;
            }
            KeyStroke keyStroke = this.terminal.readInput();
            parseKeyStroke(keyStroke);
            Thread.sleep(80);
            setKeyPressListener();
        }
    }

    public void parseKeyStroke(KeyStroke k) throws Exception {
        parsePauseKey(k);
        if (GameScreen.paused) {
            return;
        }
        switch (gameMode) {
            case CLASSIC:
                setArrowKeysListener(k);
                break;
            case PVP:
                setArrowKeysListener(k);
                setWASDListener(k);
                break;
            case PVC:
                setArrowKeysListener(k);
                break;
        }

    }

    public void setArrowKeysListener(KeyStroke k) {
        switch (k.getKeyType()) {
            case ArrowRight:
                if (snakeDirection != SnakeDirection.LEFT)
                    snakeDirection = SnakeDirection.RIGHT;
                break;
            case ArrowDown:
                if (snakeDirection != SnakeDirection.UP)
                    snakeDirection = SnakeDirection.DOWN;
                break;
            case ArrowLeft:
                if (snakeDirection != SnakeDirection.RIGHT)
                    snakeDirection = SnakeDirection.LEFT;
                break;
            case ArrowUp:
                if (snakeDirection != SnakeDirection.DOWN)
                    snakeDirection = SnakeDirection.UP;
                break;
            default:
                break;
        }
    }

    public void setWASDListener(KeyStroke k) {
        if (k.getKeyType() != KeyType.Character) {
            return;
        }
        switch (Character.toLowerCase(k.getCharacter())) {
            case 'd':
                if (snakeDirection1 != SnakeDirection.LEFT)
                    snakeDirection1 = SnakeDirection.RIGHT;
                break;
            case 's':
                if (snakeDirection1 != SnakeDirection.UP)
                    snakeDirection1 = SnakeDirection.DOWN;
                break;
            case 'a':
                if (snakeDirection1 != SnakeDirection.RIGHT)
                    snakeDirection1 = SnakeDirection.LEFT;
                break;
            case 'w':
                if (snakeDirection1 != SnakeDirection.DOWN)
                    snakeDirection1 = SnakeDirection.UP;
                break;
            default:
                break;
        }
    }

    public void parsePauseKey(KeyStroke k) throws Exception {
        if (k.getKeyType() != KeyType.Character) {
            return;
        }
        if (Character.toLowerCase(k.getCharacter()) == 'p') {
            if (!paused) {
                drawPausedBanner();
                if (bg_on) {
                    audioController.pause();
                }
                GameState.save();
            }
            if (paused) {
                // delete if file exists since it's resumed!
                File file = new File("GameState.dat");
                file.delete();
                // remove paused banner
                clearPausedBanner();
                if (bg_on) {
                    audioController.resume();
                }
                drawSnake();
                drawScore();
                Obstacle.drawObstacles();
            }
            GameScreen.paused = !GameScreen.paused;
            terminal.flush();
        }
    }
}
