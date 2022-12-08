package ph.itsmewnewbie03.snakescii;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javazoom.jl.decoder.JavaLayerException;
/**
 * Class for Playing Audio
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class AudioPlayer implements Runnable {
    SoundEffects sfx;
    PausablePlayer player;
    boolean loop = false;
    boolean stopped = false;
    boolean paused = false;
    int pausedFrame = -1;

    public void play() throws FileNotFoundException, JavaLayerException {
        String filename = "";
        switch (this.sfx) {
            case DIE:
                filename = "die.mp3";
                break;
            case EAT:
                filename = "eat.mp3";
                break;
            case START:
                filename = "start.mp3";
                break;
            case GAME_BG:
                filename = "game_bg.mp3";
                this.loop = true;
                break;
            case MAIN_BG:
                filename = "main_bg.mp3";
                this.loop = true;
                break;
            default:
                break;
        }
        InputStream is = this.getClass().getResourceAsStream("sfx/" + filename);
        player = new PausablePlayer(is);
        this.stopped = false;
        player.play();
    }

    public void pause() {
        this.player.pause();
    }

    public void resume() {
        this.player.resume();
    }

    public void stop() {
        this.stopped = true;
        this.player.stop();
    }

    public SoundEffects getSfx() {
        return sfx;
    }

    public void setSfx(SoundEffects sfx) {
        this.sfx = sfx;
    }

    @Override
    public void run() {
        try {
            playInternal();
        } catch (FileNotFoundException | JavaLayerException | InterruptedException e) {
            if(e instanceof InterruptedException){
                return;
            }
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            try {
                FileWriter myWriter = new FileWriter("audioplayerlogdump.txt");
                myWriter.write(sStackTrace + "\nCURRENT DIR: " + System.getProperty("user.dir"));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
            System.out.println(sStackTrace);
        }
    }

    private void playInternal() throws FileNotFoundException, JavaLayerException, InterruptedException {
        play();
        if (this.loop && !this.stopped) {
            while (player.getPlayerStatus() != 3) {
                // just loop until playing stops
                Thread.sleep(500);
            }
            playInternal();
        }
    }
}
