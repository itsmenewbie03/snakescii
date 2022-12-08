package ph.itsmewnewbie03.snakescii;

/**
 * Class for Controlling The Audio
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class AudioController {
    Thread t;
    AudioPlayer audioPlayer;
    volatile boolean stopped;
    boolean playing = false;

    public AudioController() {
        this.audioPlayer = new AudioPlayer();
    }

    public void play(SoundEffects soundEffects) {
        this.audioPlayer.setSfx(soundEffects);
        this.stopped = false;
        this.playing = true;
        this.t = new Thread(audioPlayer);
        this.t.start();
    }

    public void pause() {
        this.audioPlayer.pause();
    }

    public void resume() {
        this.audioPlayer.resume();;
    }

    public synchronized void stop() {
        if (!this.stopped) {
            this.stopped = true;
            this.playing = false;
            this.audioPlayer.stop();
            if (this.t != null) {
                this.t.interrupt();
            }
        }
    }
}
