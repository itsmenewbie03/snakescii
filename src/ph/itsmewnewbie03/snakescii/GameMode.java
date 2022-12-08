package ph.itsmewnewbie03.snakescii;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating The Select Mode Menu of The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameMode implements KeyListen {
    SnakeGame snakeGame;
    Terminal terminal;
    Graphics graphics;
    GameScreen gameScreen;
    boolean listening;
    public GameMode(SnakeGame snakeGame, Terminal terminal) {
        this.snakeGame = snakeGame;
        this.terminal = terminal;
        this.graphics = new Graphics();
    }

    public void draw() throws Exception {
        listening = true;
        graphics.drawGameMenu(terminal);
        Graphics.centeredTextH(this.terminal, ANSI.RED_BRIGHT, 19, "⋆ TIP - While in game press P anytime to pause. ⋆");
        Graphics.centeredTextH(this.terminal, ANSI.MAGENTA, 22, "PRESS ESCAPE TO GO BACK TO MAIN MENU", true);
        terminal.flush();
        setKeyPressListener();
    }

    @Override
    public void setKeyPressListener() throws Exception {
        if (terminal != null) {
            if (!listening) {
                return;
            }
            KeyStroke keyStroke = terminal.readInput();
            switch (keyStroke.getKeyType()) {
                case Character:
                    switch (keyStroke.getCharacter()) {
                        case '1':
                            gameScreen = new GameScreen(snakeGame, terminal, GameModes.CLASSIC);
                            listening = false;
                            gameScreen.draw();
                            break;
                        case '2':
                            gameScreen = new GameScreen(snakeGame, terminal, GameModes.PVP);
                            listening = false;
                            gameScreen.draw();
                            break;
                        case '3':
                            gameScreen = new GameScreen(snakeGame, terminal, GameModes.PVC);
                            listening = false;
                            gameScreen.draw();
                        default:
                            break;
                    }
                    break;
                case Escape:
                    snakeGame.drawMainScreen();
                    break;
                default:
                    break;

            }
            setKeyPressListener();
        }

    }

}
