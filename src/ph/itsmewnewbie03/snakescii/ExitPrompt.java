package ph.itsmewnewbie03.snakescii;
import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Inflating ExitPrompt
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class ExitPrompt implements KeyListen{
    Terminal terminal;
    SnakeGame snakeGame;
    private boolean keyListen;
    public ExitPrompt(SnakeGame snakeGame,Terminal terminal) {
        this.terminal = terminal;
        this.snakeGame = snakeGame;
    }

    public void drawExitPrompt() throws IOException {
        terminal.clearScreen();
        keyListen = true;
        final TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(32, 6, "█");
        textGraphics.putString(33, 6, "█");
        textGraphics.putString(34, 6, "█");
        textGraphics.putString(35, 6, "█");
        textGraphics.putString(36, 6, "█");
        textGraphics.putString(37, 6, "█");
        textGraphics.putString(38, 6, "█");
        textGraphics.putString(39, 6, "╗");
        textGraphics.putString(40, 6, "█");
        textGraphics.putString(41, 6, "█");
        textGraphics.putString(42, 6, "╗");
        textGraphics.putString(43, 6, "░");
        textGraphics.putString(44, 6, "░");
        textGraphics.putString(45, 6, "█");
        textGraphics.putString(46, 6, "█");
        textGraphics.putString(47, 6, "╗");
        textGraphics.putString(48, 6, "█");
        textGraphics.putString(49, 6, "█");
        textGraphics.putString(50, 6, "╗");
        textGraphics.putString(51, 6, "█");
        textGraphics.putString(52, 6, "█");
        textGraphics.putString(53, 6, "█");
        textGraphics.putString(54, 6, "█");
        textGraphics.putString(55, 6, "█");
        textGraphics.putString(56, 6, "█");
        textGraphics.putString(57, 6, "█");
        textGraphics.putString(58, 6, "█");
        textGraphics.putString(59, 6, "╗");
        textGraphics.putString(60, 6, "░");
        textGraphics.putString(61, 6, "█");
        textGraphics.putString(62, 6, "█");
        textGraphics.putString(63, 6, "█");
        textGraphics.putString(64, 6, "█");
        textGraphics.putString(65, 6, "█");
        textGraphics.putString(66, 6, "╗");
        textGraphics.putString(67, 6, "░");
        textGraphics.putString(32, 7, "█");
        textGraphics.putString(33, 7, "█");
        textGraphics.putString(34, 7, "╔");
        textGraphics.putString(35, 7, "═");
        textGraphics.putString(36, 7, "═");
        textGraphics.putString(37, 7, "═");
        textGraphics.putString(38, 7, "═");
        textGraphics.putString(39, 7, "╝");
        textGraphics.putString(40, 7, "╚");
        textGraphics.putString(41, 7, "█");
        textGraphics.putString(42, 7, "█");
        textGraphics.putString(43, 7, "╗");
        textGraphics.putString(44, 7, "█");
        textGraphics.putString(45, 7, "█");
        textGraphics.putString(46, 7, "╔");
        textGraphics.putString(47, 7, "╝");
        textGraphics.putString(48, 7, "█");
        textGraphics.putString(49, 7, "█");
        textGraphics.putString(50, 7, "║");
        textGraphics.putString(51, 7, "╚");
        textGraphics.putString(52, 7, "═");
        textGraphics.putString(53, 7, "═");
        textGraphics.putString(54, 7, "█");
        textGraphics.putString(55, 7, "█");
        textGraphics.putString(56, 7, "╔");
        textGraphics.putString(57, 7, "═");
        textGraphics.putString(58, 7, "═");
        textGraphics.putString(59, 7, "╝");
        textGraphics.putString(60, 7, "█");
        textGraphics.putString(61, 7, "█");
        textGraphics.putString(62, 7, "╔");
        textGraphics.putString(63, 7, "═");
        textGraphics.putString(64, 7, "═");
        textGraphics.putString(65, 7, "█");
        textGraphics.putString(66, 7, "█");
        textGraphics.putString(67, 7, "╗");
        textGraphics.putString(32, 8, "█");
        textGraphics.putString(33, 8, "█");
        textGraphics.putString(34, 8, "█");
        textGraphics.putString(35, 8, "█");
        textGraphics.putString(36, 8, "█");
        textGraphics.putString(37, 8, "╗");
        textGraphics.putString(38, 8, "░");
        textGraphics.putString(39, 8, "░");
        textGraphics.putString(40, 8, "░");
        textGraphics.putString(41, 8, "╚");
        textGraphics.putString(42, 8, "█");
        textGraphics.putString(43, 8, "█");
        textGraphics.putString(44, 8, "█");
        textGraphics.putString(45, 8, "╔");
        textGraphics.putString(46, 8, "╝");
        textGraphics.putString(47, 8, "░");
        textGraphics.putString(48, 8, "█");
        textGraphics.putString(49, 8, "█");
        textGraphics.putString(50, 8, "║");
        textGraphics.putString(51, 8, "░");
        textGraphics.putString(52, 8, "░");
        textGraphics.putString(53, 8, "░");
        textGraphics.putString(54, 8, "█");
        textGraphics.putString(55, 8, "█");
        textGraphics.putString(56, 8, "║");
        textGraphics.putString(57, 8, "░");
        textGraphics.putString(58, 8, "░");
        textGraphics.putString(59, 8, "░");
        textGraphics.putString(60, 8, "╚");
        textGraphics.putString(61, 8, "═");
        textGraphics.putString(62, 8, "╝");
        textGraphics.putString(63, 8, "█");
        textGraphics.putString(64, 8, "█");
        textGraphics.putString(65, 8, "█");
        textGraphics.putString(66, 8, "╔");
        textGraphics.putString(67, 8, "╝");
        textGraphics.putString(32, 9, "█");
        textGraphics.putString(33, 9, "█");
        textGraphics.putString(34, 9, "╔");
        textGraphics.putString(35, 9, "═");
        textGraphics.putString(36, 9, "═");
        textGraphics.putString(37, 9, "╝");
        textGraphics.putString(38, 9, "░");
        textGraphics.putString(39, 9, "░");
        textGraphics.putString(40, 9, "░");
        textGraphics.putString(41, 9, "█");
        textGraphics.putString(42, 9, "█");
        textGraphics.putString(43, 9, "╔");
        textGraphics.putString(44, 9, "█");
        textGraphics.putString(45, 9, "█");
        textGraphics.putString(46, 9, "╗");
        textGraphics.putString(47, 9, "░");
        textGraphics.putString(48, 9, "█");
        textGraphics.putString(49, 9, "█");
        textGraphics.putString(50, 9, "║");
        textGraphics.putString(51, 9, "░");
        textGraphics.putString(52, 9, "░");
        textGraphics.putString(53, 9, "░");
        textGraphics.putString(54, 9, "█");
        textGraphics.putString(55, 9, "█");
        textGraphics.putString(56, 9, "║");
        textGraphics.putString(57, 9, "░");
        textGraphics.putString(58, 9, "░");
        textGraphics.putString(59, 9, "░");
        textGraphics.putString(60, 9, "░");
        textGraphics.putString(61, 9, "░");
        textGraphics.putString(62, 9, "░");
        textGraphics.putString(63, 9, "╚");
        textGraphics.putString(64, 9, "═");
        textGraphics.putString(65, 9, "═");
        textGraphics.putString(66, 9, "╝");
        textGraphics.putString(67, 9, "░");
        textGraphics.putString(32, 10, "█");
        textGraphics.putString(33, 10, "█");
        textGraphics.putString(34, 10, "█");
        textGraphics.putString(35, 10, "█");
        textGraphics.putString(36, 10, "█");
        textGraphics.putString(37, 10, "█");
        textGraphics.putString(38, 10, "█");
        textGraphics.putString(39, 10, "╗");
        textGraphics.putString(40, 10, "█");
        textGraphics.putString(41, 10, "█");
        textGraphics.putString(42, 10, "╔");
        textGraphics.putString(43, 10, "╝");
        textGraphics.putString(44, 10, "╚");
        textGraphics.putString(45, 10, "█");
        textGraphics.putString(46, 10, "█");
        textGraphics.putString(47, 10, "╗");
        textGraphics.putString(48, 10, "█");
        textGraphics.putString(49, 10, "█");
        textGraphics.putString(50, 10, "║");
        textGraphics.putString(51, 10, "░");
        textGraphics.putString(52, 10, "░");
        textGraphics.putString(53, 10, "░");
        textGraphics.putString(54, 10, "█");
        textGraphics.putString(55, 10, "█");
        textGraphics.putString(56, 10, "║");
        textGraphics.putString(57, 10, "░");
        textGraphics.putString(58, 10, "░");
        textGraphics.putString(59, 10, "░");
        textGraphics.putString(60, 10, "░");
        textGraphics.putString(61, 10, "░");
        textGraphics.putString(62, 10, "░");
        textGraphics.putString(63, 10, "█");
        textGraphics.putString(64, 10, "█");
        textGraphics.putString(65, 10, "╗");
        textGraphics.putString(66, 10, "░");
        textGraphics.putString(67, 10, "░");
        textGraphics.putString(32, 11, "╚");
        textGraphics.putString(33, 11, "═");
        textGraphics.putString(34, 11, "═");
        textGraphics.putString(35, 11, "═");
        textGraphics.putString(36, 11, "═");
        textGraphics.putString(37, 11, "═");
        textGraphics.putString(38, 11, "═");
        textGraphics.putString(39, 11, "╝");
        textGraphics.putString(40, 11, "╚");
        textGraphics.putString(41, 11, "═");
        textGraphics.putString(42, 11, "╝");
        textGraphics.putString(43, 11, "░");
        textGraphics.putString(44, 11, "░");
        textGraphics.putString(45, 11, "╚");
        textGraphics.putString(46, 11, "═");
        textGraphics.putString(47, 11, "╝");
        textGraphics.putString(48, 11, "╚");
        textGraphics.putString(49, 11, "═");
        textGraphics.putString(50, 11, "╝");
        textGraphics.putString(51, 11, "░");
        textGraphics.putString(52, 11, "░");
        textGraphics.putString(53, 11, "░");
        textGraphics.putString(54, 11, "╚");
        textGraphics.putString(55, 11, "═");
        textGraphics.putString(56, 11, "╝");
        textGraphics.putString(57, 11, "░");
        textGraphics.putString(58, 11, "░");
        textGraphics.putString(59, 11, "░");
        textGraphics.putString(60, 11, "░");
        textGraphics.putString(61, 11, "░");
        textGraphics.putString(62, 11, "░");
        textGraphics.putString(63, 11, "╚");
        textGraphics.putString(64, 11, "═");
        textGraphics.putString(65, 11, "╝");
        textGraphics.putString(66, 11, "░");
        textGraphics.putString(67, 11, "░");
        textGraphics.putString(36, 12, "█");
        textGraphics.putString(37, 12, "█");
        textGraphics.putString(38, 12, "╗");
        textGraphics.putString(39, 12, "░");
        textGraphics.putString(40, 12, "░");
        textGraphics.putString(41, 12, "░");
        textGraphics.putString(42, 12, "█");
        textGraphics.putString(43, 12, "█");
        textGraphics.putString(44, 12, "╗");
        textGraphics.putString(45, 12, " ");
        textGraphics.putString(46, 12, " ");
        textGraphics.putString(47, 12, "░");
        textGraphics.putString(48, 12, "░");
        textGraphics.putString(49, 12, "░");
        textGraphics.putString(50, 12, "░");
        textGraphics.putString(51, 12, "█");
        textGraphics.putString(52, 12, "█");
        textGraphics.putString(53, 12, "╗");
        textGraphics.putString(54, 12, " ");
        textGraphics.putString(55, 12, " ");
        textGraphics.putString(56, 12, "█");
        textGraphics.putString(57, 12, "█");
        textGraphics.putString(58, 12, "█");
        textGraphics.putString(59, 12, "╗");
        textGraphics.putString(60, 12, "░");
        textGraphics.putString(61, 12, "░");
        textGraphics.putString(62, 12, "█");
        textGraphics.putString(63, 12, "█");
        textGraphics.putString(64, 12, "╗");
        textGraphics.putString(36, 13, "╚");
        textGraphics.putString(37, 13, "█");
        textGraphics.putString(38, 13, "█");
        textGraphics.putString(39, 13, "╗");
        textGraphics.putString(40, 13, "░");
        textGraphics.putString(41, 13, "█");
        textGraphics.putString(42, 13, "█");
        textGraphics.putString(43, 13, "╔");
        textGraphics.putString(44, 13, "╝");
        textGraphics.putString(45, 13, " ");
        textGraphics.putString(46, 13, " ");
        textGraphics.putString(47, 13, "░");
        textGraphics.putString(48, 13, "░");
        textGraphics.putString(49, 13, "░");
        textGraphics.putString(50, 13, "█");
        textGraphics.putString(51, 13, "█");
        textGraphics.putString(52, 13, "╔");
        textGraphics.putString(53, 13, "╝");
        textGraphics.putString(54, 13, " ");
        textGraphics.putString(55, 13, " ");
        textGraphics.putString(56, 13, "█");
        textGraphics.putString(57, 13, "█");
        textGraphics.putString(58, 13, "█");
        textGraphics.putString(59, 13, "█");
        textGraphics.putString(60, 13, "╗");
        textGraphics.putString(61, 13, "░");
        textGraphics.putString(62, 13, "█");
        textGraphics.putString(63, 13, "█");
        textGraphics.putString(64, 13, "║");
        textGraphics.putString(36, 14, "░");
        textGraphics.putString(37, 14, "╚");
        textGraphics.putString(38, 14, "█");
        textGraphics.putString(39, 14, "█");
        textGraphics.putString(40, 14, "█");
        textGraphics.putString(41, 14, "█");
        textGraphics.putString(42, 14, "╔");
        textGraphics.putString(43, 14, "╝");
        textGraphics.putString(44, 14, "░");
        textGraphics.putString(45, 14, " ");
        textGraphics.putString(46, 14, " ");
        textGraphics.putString(47, 14, "░");
        textGraphics.putString(48, 14, "░");
        textGraphics.putString(49, 14, "█");
        textGraphics.putString(50, 14, "█");
        textGraphics.putString(51, 14, "╔");
        textGraphics.putString(52, 14, "╝");
        textGraphics.putString(53, 14, "░");
        textGraphics.putString(54, 14, " ");
        textGraphics.putString(55, 14, " ");
        textGraphics.putString(56, 14, "█");
        textGraphics.putString(57, 14, "█");
        textGraphics.putString(58, 14, "╔");
        textGraphics.putString(59, 14, "█");
        textGraphics.putString(60, 14, "█");
        textGraphics.putString(61, 14, "╗");
        textGraphics.putString(62, 14, "█");
        textGraphics.putString(63, 14, "█");
        textGraphics.putString(64, 14, "║");
        textGraphics.putString(36, 15, "░");
        textGraphics.putString(37, 15, "░");
        textGraphics.putString(38, 15, "╚");
        textGraphics.putString(39, 15, "█");
        textGraphics.putString(40, 15, "█");
        textGraphics.putString(41, 15, "╔");
        textGraphics.putString(42, 15, "╝");
        textGraphics.putString(43, 15, "░");
        textGraphics.putString(44, 15, "░");
        textGraphics.putString(45, 15, " ");
        textGraphics.putString(46, 15, " ");
        textGraphics.putString(47, 15, "░");
        textGraphics.putString(48, 15, "█");
        textGraphics.putString(49, 15, "█");
        textGraphics.putString(50, 15, "╔");
        textGraphics.putString(51, 15, "╝");
        textGraphics.putString(52, 15, "░");
        textGraphics.putString(53, 15, "░");
        textGraphics.putString(54, 15, " ");
        textGraphics.putString(55, 15, " ");
        textGraphics.putString(56, 15, "█");
        textGraphics.putString(57, 15, "█");
        textGraphics.putString(58, 15, "║");
        textGraphics.putString(59, 15, "╚");
        textGraphics.putString(60, 15, "█");
        textGraphics.putString(61, 15, "█");
        textGraphics.putString(62, 15, "█");
        textGraphics.putString(63, 15, "█");
        textGraphics.putString(64, 15, "║");
        textGraphics.putString(36, 16, "░");
        textGraphics.putString(37, 16, "░");
        textGraphics.putString(38, 16, "░");
        textGraphics.putString(39, 16, "█");
        textGraphics.putString(40, 16, "█");
        textGraphics.putString(41, 16, "║");
        textGraphics.putString(42, 16, "░");
        textGraphics.putString(43, 16, "░");
        textGraphics.putString(44, 16, "░");
        textGraphics.putString(45, 16, " ");
        textGraphics.putString(46, 16, " ");
        textGraphics.putString(47, 16, "█");
        textGraphics.putString(48, 16, "█");
        textGraphics.putString(49, 16, "╔");
        textGraphics.putString(50, 16, "╝");
        textGraphics.putString(51, 16, "░");
        textGraphics.putString(52, 16, "░");
        textGraphics.putString(53, 16, "░");
        textGraphics.putString(54, 16, " ");
        textGraphics.putString(55, 16, " ");
        textGraphics.putString(56, 16, "█");
        textGraphics.putString(57, 16, "█");
        textGraphics.putString(58, 16, "║");
        textGraphics.putString(59, 16, "░");
        textGraphics.putString(60, 16, "╚");
        textGraphics.putString(61, 16, "█");
        textGraphics.putString(62, 16, "█");
        textGraphics.putString(63, 16, "█");
        textGraphics.putString(64, 16, "║");
        textGraphics.putString(36, 17, "░");
        textGraphics.putString(37, 17, "░");
        textGraphics.putString(38, 17, "░");
        textGraphics.putString(39, 17, "╚");
        textGraphics.putString(40, 17, "═");
        textGraphics.putString(41, 17, "╝");
        textGraphics.putString(42, 17, "░");
        textGraphics.putString(43, 17, "░");
        textGraphics.putString(44, 17, "░");
        textGraphics.putString(45, 17, " ");
        textGraphics.putString(46, 17, " ");
        textGraphics.putString(47, 17, "╚");
        textGraphics.putString(48, 17, "═");
        textGraphics.putString(49, 17, "╝");
        textGraphics.putString(50, 17, "░");
        textGraphics.putString(51, 17, "░");
        textGraphics.putString(52, 17, "░");
        textGraphics.putString(53, 17, "░");
        textGraphics.putString(54, 17, " ");
        textGraphics.putString(55, 17, " ");
        textGraphics.putString(56, 17, "╚");
        textGraphics.putString(57, 17, "═");
        textGraphics.putString(58, 17, "╝");
        textGraphics.putString(59, 17, "░");
        textGraphics.putString(60, 17, "░");
        textGraphics.putString(61, 17, "╚");
        textGraphics.putString(62, 17, "═");
        textGraphics.putString(63, 17, "═");
        textGraphics.putString(64, 17, "╝");
                
        terminal.flush();
    }

    public void stopKeyPressListener(){
        keyListen = false;
    }
    @Override
    public void setKeyPressListener() throws Exception {
        if (this.terminal != null) {
            KeyStroke keyStroke = this.terminal.readInput();
            while (keyListen) {
                switch (Character.toLowerCase(keyStroke.getCharacter())) {
                    case 'y':
                        stopKeyPressListener();
                        terminal.close();
                        break;
                    case 'n':
                        stopKeyPressListener();
                        snakeGame.drawMainScreen();
                        break;
                    default:
                        break;
                        // System.out.println(keyStroke.toString());
                }
                keyStroke = this.terminal.readInput();
            }
        }
    }
}
