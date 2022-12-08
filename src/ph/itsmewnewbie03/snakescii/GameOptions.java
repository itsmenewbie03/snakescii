package ph.itsmewnewbie03.snakescii;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating The Menu Section of The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameOptions implements KeyListen {
    SnakeGame snakeGame;
    Terminal terminal;
    Graphics graphics;
    private int current_index = 0;
    private int speed = 1;
    private boolean has_obstacle = false;
    private boolean bg_on = false;
    private boolean sfx_on = false;

    public GameOptions(SnakeGame snakeGame, Terminal terminal) {
        this.snakeGame = snakeGame;
        this.terminal = terminal;
        this.graphics = new Graphics();
    }

    public void draw() throws Exception {
        graphics.drawOptionsScreen(terminal);
        drawOptions();
        setKeyPressListener();
    }

    public void drawOptions() throws Exception {
        int pointer_index = 13 + this.current_index;
        final TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        speed = SnakeGame.gameConfig.getSpeed();
        has_obstacle = SnakeGame.gameConfig.has_obstacle();
        bg_on = SnakeGame.gameConfig.isBg_on();
        sfx_on = SnakeGame.gameConfig.isSfx_on();
        Graphics.centeredTextH(terminal, textGraphics, 11, "-".repeat(53));
        Graphics.centeredTextH(terminal, textGraphics, 13, "Speed: " + speed);
        Graphics.centeredTextH(terminal, textGraphics, 14, "Obstacles: " + (has_obstacle ? "ON" : "OFF"));
        Graphics.centeredTextH(terminal, textGraphics, 15, "Background Music: " + (bg_on ? "ON" : "OFF"));
        Graphics.centeredTextH(terminal, textGraphics, 16, "Sound Effects: " + (sfx_on ? "ON" : "OFF"));
        Graphics.centeredTextH(terminal, textGraphics, 18, "-".repeat(53));
        textGraphics.putString(24, pointer_index, ">",SGR.BOLD);
        textGraphics.putString(24 + 52, pointer_index, "<",SGR.BOLD);
        Graphics.centeredTextH(terminal, ANSI.CYAN, 20, "⋆ PRESS LEFT OR RIGHT ARROW KEY TO ADJUST ⋆");
        Graphics.centeredTextH(terminal, ANSI.MAGENTA, 23, "PRESS ESCAPE TO GO BACK TO MAIN MENU", true);
        terminal.flush();
    }

    @Override
    public void setKeyPressListener() throws Exception {
        if (terminal != null) {
            KeyStroke keyStroke = terminal.readInput();
            switch (keyStroke.getKeyType()) {
                case Escape:
                    snakeGame.drawMainScreen();
                case ArrowDown:
                    if (this.current_index < 3) {
                        this.current_index++;
                        draw();
                    }
                    break;
                case ArrowUp:
                    if (this.current_index > 0) {
                        this.current_index--;
                        draw();
                    }
                    break;
                case ArrowLeft:
                    switch (this.current_index) {
                        case 0:
                            if (this.speed > 1) {
                                SnakeGame.gameConfig.setSpeed(this.speed-1);
                            }
                            break;
                        case 1:
                            if (this.has_obstacle) {
                                SnakeGame.gameConfig.setHas_obstacle(false);
                            }
                            break;
                        case 2:
                            if (this.bg_on) {
                                SnakeGame.gameConfig.setBg_on(false);
                            }
                            break;
                        case 3:
                            if (this.sfx_on) {
                                SnakeGame.gameConfig.setSfx_on(false);
                            }
                            break;
                        default:
                            break;
                    }
                    draw();
                    break;
                case ArrowRight:
                    switch (this.current_index) {
                        case 0:
                            if (this.speed < 10) {
                                SnakeGame.gameConfig.setSpeed(this.speed + 1);
                            }
                            break;
                        case 1:
                            if (!this.has_obstacle) {
                                SnakeGame.gameConfig.setHas_obstacle(true);
                            }
                            break;
                        case 2:
                            if (!this.bg_on) {
                                SnakeGame.gameConfig.setBg_on(true);
                            }
                            break;
                        case 3:
                            if (!this.sfx_on) {
                                SnakeGame.gameConfig.setSfx_on(true);
                            }
                            break;
                        default:
                            break;
                    }
                    draw();
                    break;
                default:
                    break;

            }
            setKeyPressListener();
        }

    }

}
