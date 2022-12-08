package ph.itsmewnewbie03.snakescii;
import java.util.ArrayList;
import java.util.Set;
/**
 * Class to monitor if the window is closed and terminate the program if so. 
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class WindowMonitor implements Runnable {
    SnakeGame s;

    public WindowMonitor(SnakeGame s) {
        this.s = s;
    }

    @Override
    public void run() {
        String watchForKillThread = "DestroyJavaVM";
        String watchForThread = "AWT-Shutdown";
        ArrayList<String> aliveThreads = new ArrayList<>();
        while (true) {
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            for (Thread thread : threadSet) {
                aliveThreads.add(thread.getName());
            }
            if (aliveThreads.contains(watchForKillThread) || !aliveThreads.contains(watchForThread)) {
                GameScreen.setTerminated(true);
                System.exit(0);
            }
            aliveThreads.clear();
        }
    }

}
