package ph.itsmewnewbie03.snakescii;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
/**
 * Class for Saving and Loading Game State
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameState {
    private static final String AES_KEY = "thisisnotthekey!";
    private static AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();

    public static void save() {
        // format
        // MODE|SNAKE1|SNAKE2 or SNAKE AI|FOOD|OBSTACLES
        // dumps currents class state to a file
        try {
            ArrayList<String> data = new ArrayList<>();
            FileOutputStream fos = new FileOutputStream("GameState.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            String gameMode = GameScreen.gameMode.toString();
            String mode = gameMode;
            data.add(mode);
            switch (GameScreen.gameMode) {
                case CLASSIC:
                    String snake = Snake.snake.toString();
                    data.add(snake);
                    // add dummy data to make parsing easy
                    data.add("[NULL]");
                    break;
                case PVC:
                    String player1 = Snake.snake.toString();
                    data.add(player1);
                    String aisnake = SnakeAI.snake.toString();
                    data.add(aisnake);
                    break;
                case PVP:
                    String player_1 = Snake.snake.toString();
                    data.add(player_1);
                    String player_2 = Snake1.snake.toString();
                    data.add(player_2);
                    break;
                default:
                    break;
            }
            String food_coordinate = Food.coordinate.toString();
            String[] food_coordinate_arr = { food_coordinate };
            data.add(Arrays.toString(food_coordinate_arr));
            String obstacles = Obstacle.coordinates.size() > 0 ? Obstacle.coordinates.toString() : "[NULL]";
            data.add(obstacles);
            oos.writeUTF(aesEncryptionDecryption.encrypt(data.toString(), AES_KEY));
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return MODE|SNAKE1|SNAKE2 or SNAKE AI|FOOD|OBSTACLES
     * @throws IOException
     */
    public static String[] load() throws IOException {
        // check if state file exists
        File gameFile = new File("GameState.dat");
        if (gameFile.isFile()) {
            // load the last mode played
            // try to parse this
            // [PVC, [(47,12), (48,12), (49,12), (50,12)], [(69,4), (70,4), (71,4), (72,4)],
            // [(96,4)], [(44,19), (69,8), (8,15), (78,6), (50,20), (90,7)]]
            // 0 1 2 3 4
            // format MODE|SNAKE1|SNAKE2 or SNAKE AI|FOOD|OBSTACLES
            FileInputStream is = new FileInputStream("GameState.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
            String saved = aesEncryptionDecryption.decrypt(ois.readUTF(), AES_KEY);
            // System.out.println(saved);
            String[] saved_parsed = saved.substring(1, saved.length() - 1).replaceAll(",\s\\[", "|[").split("\\|");
            ois.close();
            is.close();
            gameFile.delete();
            return saved_parsed;
        }
        return null;
    }

    public static Stack<Coordinate> buildStackFromString(String toParse) {
        Stack<Coordinate> builtStack = new Stack<>();
        String parsed = toParse.replaceAll("(\\[|\\])", "").replaceAll(",\s+", "|");
        String[] parsed_coordinates = parsed.split("\\|");
        for (String coordinate : parsed_coordinates) {
            builtStack.add(new Coordinate(coordinate));
        }
        return builtStack;
    }

    // public static void main(String[] args) throws Exception {
    //     load();
    // }
}
