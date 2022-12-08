package ph.itsmewnewbie03.snakescii;

import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating The About Section in The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameAbout implements KeyListen {
    SnakeGame s;
    Terminal terminal;
    Graphics graphics;

    public GameAbout(SnakeGame s, Terminal terminal) {
        this.s = s;
        this.terminal = terminal;
        this.graphics = new Graphics();
    }
    public void draw() throws Exception {
        graphics.drawAboutScreen(terminal);
        drawAbout();
        setKeyPressListener();
    }
    private void drawAbout() throws Exception{
        Graphics.centeredTextH(terminal, ANSI.CYAN, 12, "SnakeSCII: SnakeCII is a classic snake game with a twist.");
        Graphics.centeredTextH(terminal, ANSI.CYAN, 13, "The graphics of this game is purely ASCII based.");
        Graphics.centeredTextH(terminal, ANSI.CYAN, 14, "The objective of this game is to score as much as possible or beat your opponent.");
        Graphics.centeredTextH(terminal, ANSI.RED_BRIGHT, 20, "Finished at: Dec 1, 2022 at 5:26:36 PM ");
        Graphics.centeredTextH(terminal, ANSI.RED_BRIGHT, 21, "Developed with ♥ and ☕︎ by: Salvador, 1 M. Ligayao.");
        
        Graphics.centeredTextH(this.terminal, ANSI.MAGENTA, 23, "PRESS ESCAPE TO GO BACK TO MAIN MENU", true);
        terminal.flush();
    }
    @Override
    public void setKeyPressListener() throws Exception {
        if (terminal != null) {
            KeyStroke keyStroke = terminal.readInput();
            switch (keyStroke.getKeyType()) {
                case Escape:
                    s.drawMainScreen();
                default:
                    break;

            }
            setKeyPressListener();
        }

    }

}
