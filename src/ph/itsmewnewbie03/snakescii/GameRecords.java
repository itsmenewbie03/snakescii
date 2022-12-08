package ph.itsmewnewbie03.snakescii;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Class for Manipulating The Records Section of The Game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameRecords implements KeyListen{
    SnakeGame s;
    Terminal terminal;
    Graphics graphics;
    public GameRecords(SnakeGame s,Terminal terminal) {
        this.s = s;
        this.terminal = terminal;
        this.graphics = new Graphics();
    }
    public void draw() throws Exception{
        graphics.drawRecordsScreen(terminal);
        drawRecords();
        setKeyPressListener();
    }
    public void drawRecords() throws Exception{
        final TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        GameConfig config = SnakeGame.gameConfig;
        String high_score = String.valueOf(config.getHigh_score());
        String ai_win_count = String.valueOf(config.getAi_win_count());
        String player_win_count = String.valueOf(config.getPlayer_win_count());
        Graphics.centeredTextH(terminal, textGraphics, 11, String.format("%s %s %s","-".repeat(16),"CLASSIC","-".repeat(15)));
        Graphics.centeredTextH(terminal, textGraphics, 12, "HIGHEST SCORE: "+high_score);
        Graphics.centeredTextH(terminal, textGraphics, 14, String.format("%s %s %s","-".repeat(10),"PLAYER VS COMPUTER","-".repeat(10)));
        Graphics.centeredTextH(terminal, textGraphics, 15, "PLAYER WIN COUNT: "+ player_win_count);
        Graphics.centeredTextH(terminal, textGraphics, 16, "COMPUTER WIN COUNT: "+ ai_win_count);
        Graphics.centeredTextH(terminal, ANSI.MAGENTA, 22, "PRESS ESCAPE TO GO BACK TO MAIN MENU", true);
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
