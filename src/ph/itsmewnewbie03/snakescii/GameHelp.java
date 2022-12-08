package ph.itsmewnewbie03.snakescii;

import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating The Help Section of The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameHelp implements KeyListen {
    SnakeGame s;
    Terminal terminal;
    Graphics graphics;

    public GameHelp(SnakeGame s, Terminal terminal) {
        this.s = s;
        this.terminal = terminal;
        this.graphics = new Graphics();
    }

    public void draw() throws Exception {
        graphics.drawHelpScreen(terminal);
        drawHelp();
        setKeyPressListener();
    }

    public void drawHelp() throws Exception {
        Graphics.centeredTextH(this.terminal,ANSI.MAGENTA,9,String.format("%s %s %s","-".repeat(16),"CHARACTERS","-".repeat(15)));
        Graphics.centeredTextH(terminal, ANSI.CYAN,ANSI.WHITE_BRIGHT, 10, "۝ - Use the arrow keys to control the movement of this snake.","-");
        Graphics.centeredTextH(terminal, ANSI.MAGENTA,ANSI.WHITE_BRIGHT, 11, "۝ - Use WASD keys to control the movement of this snake.","-");
        Graphics.centeredTextH(terminal, ANSI.RED_BRIGHT,ANSI.WHITE_BRIGHT, 12, "♥ - Move your snake toward this red heart to earn points and grow.","-");
        Graphics.centeredTextH(terminal, ANSI.BLUE_BRIGHT,ANSI.WHITE_BRIGHT, 13, "卍 - This is the game's obstacle, hit this and the game will be over","-");
        Graphics.centeredTextH(this.terminal,ANSI.MAGENTA,14,String.format("%s %s %s","-".repeat(18),"MODES","-".repeat(18)));
        Graphics.centeredTextH(terminal, ANSI.WHITE_BRIGHT, 15, "CLASSIC - Score as much as possible, hitting the wall or yourself will end the game.");
        Graphics.centeredTextH(terminal, ANSI.WHITE_BRIGHT, 16, "PVP - Be the last one standing, eat, grow and trap your opponent into hitting you.");
        Graphics.centeredTextH(terminal, ANSI.WHITE_BRIGHT, 17, "But be careful not to kill yourself.");
        Graphics.centeredTextH(terminal, ANSI.WHITE_BRIGHT, 18, "PVC - Got no one to play with? Try your skills against an AI.");
        Graphics.centeredTextH(this.terminal,ANSI.MAGENTA,19,String.format("%s %s %s","-".repeat(17),"OPTIONS","-".repeat(17)));
        Graphics.centeredTextH(this.terminal,ANSI.WHITE_BRIGHT,20,"Configure the game as you like. Enable or disable music, sound effects or obstacle.");
        Graphics.centeredTextH(this.terminal,ANSI.WHITE_BRIGHT,21,"Speed can also be adjust to match your preference.");
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
