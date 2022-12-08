package ph.itsmewnewbie03.snakescii;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * The main entry point of the game
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class SnakeGame implements KeyListen, GameMethods {
    private final int rows = 25;
    private final int cols = 101;
    Terminal terminal;
    Graphics graphics;
    ExitPrompt exitPrompt;
    GameMode gameMode;
    WindowMonitor windowMonitor;
    GameRecords gameRecords;
    GameOptions gameOptions;
    GameHelp gameHelp;
    GameAbout gameAbout;
    AudioController audio;
    static GameConfig gameConfig;
    static Database database;

    public SnakeGame() {
        graphics = new Graphics();
    }

    @Override
    public void initializeGame() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        TerminalSize initialTerminalSize = new TerminalSize(this.cols, this.rows);
        defaultTerminalFactory.setInitialTerminalSize(initialTerminalSize);
        defaultTerminalFactory.setTerminalEmulatorTitle("SnakeSCII");
        terminal = null;
        try {
            terminal = defaultTerminalFactory.createTerminal();
            windowMonitor = new WindowMonitor(this);
            Thread windowMonitorThread = new Thread(windowMonitor);
            windowMonitorThread.start();
            exitPrompt = new ExitPrompt(this, terminal);
            gameMode = new GameMode(this, terminal);
            database = new Database();
            database.setDefaultConfig();
            gameRecords = new GameRecords(this, terminal);
            gameOptions = new GameOptions(this, terminal);
            gameHelp = new GameHelp(this, terminal);
            gameAbout = new GameAbout(this, terminal);
            audio = new AudioController();
            drawMainScreen();
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

    @Override
    public void drawMainScreen() throws Exception {
        if (terminal != null) {
            terminal.clearScreen();
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);
            graphics.drawBanner(terminal);
            graphics.drawMenu(terminal);
            terminal.addResizeListener((terminal1, newSize) -> {
                try {
                    graphics.showResizeWarning(terminal1, newSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            gameConfig = new GameConfig(database.getConfig());
            if (gameConfig.isBg_on()) {
                if(!audio.playing){
                    audio.play(SoundEffects.MAIN_BG);
                }
            } else {
                if(audio.playing){
                    audio.stop();
                }
            }
            terminal.flush();
            setKeyPressListener();
        }
    }

    @Override
    public void setKeyPressListener() throws Exception {
        if (this.terminal != null) {
            KeyStroke keyStroke = this.terminal.readInput();
            while (true) {
                switch (keyStroke.getKeyType()) {
                    case Character:
                        switch (Character.toLowerCase(keyStroke.getCharacter())) {
                            case 'e':
                                exitPrompt.drawExitPrompt();
                                exitPrompt.setKeyPressListener();
                                return;
                            case 'n':
                                gameMode.draw();
                                return;
                            case 'r':
                                gameRecords.draw();
                                return;
                            case 'o':
                                gameOptions.draw();
                                return;
                            case 'h':
                                gameHelp.draw();
                                return;
                            case 'a':
                                gameAbout.draw();
                                return;
                            default:
                                break;
                            // System.out.println(keyStroke.toString());
                        }
                        break;
                    default:
                        break;
                }
                keyStroke = this.terminal.readInput();
            }

        }

    }

}
