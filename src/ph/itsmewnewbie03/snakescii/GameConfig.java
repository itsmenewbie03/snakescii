package ph.itsmewnewbie03.snakescii;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class for Interacting with the Database Class
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class GameConfig {
    private int high_score;
    private int speed;
    private boolean has_obstacle;
    private int ai_win_count;
    private int player_win_count;
    private boolean sfx_on;
    private boolean bg_on;

    public GameConfig(ResultSet rs) throws SQLException {
        while (rs.next()) {
            switch (rs.getString("key")) {
                case "speed":
                    this.speed = rs.getInt("value");
                    break;
                case "high_score":
                    this.high_score = rs.getInt("value");
                    break;
                case "has_obstacle":
                    this.has_obstacle = rs.getInt("value") == 1;
                    break;
                case "player_win_count":
                    this.player_win_count = rs.getInt("value");
                    break;
                case "ai_win_count":
                    this.ai_win_count = rs.getInt("value");
                    break;
                case "sfx_on":
                    this.sfx_on = rs.getInt("value") == 1;
                    break;
                case "bg_on":
                    this.bg_on = rs.getInt("value") == 1;
                    break;
                default:
                    break;
            }
        }
    }

    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) throws SQLException {
        SnakeGame.database.updateConfig("high_score", high_score);
        this.high_score = high_score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) throws SQLException {
        SnakeGame.database.updateConfig("speed", speed);
        this.speed = speed;
    }

    public boolean has_obstacle() {
        return has_obstacle;
    }

    public void setHas_obstacle(boolean has_obstacle) throws SQLException {
        SnakeGame.database.updateConfig("has_obstacle", has_obstacle ? 1 : 0);
        this.has_obstacle = has_obstacle;
    }

    public int getAi_win_count() {
        return ai_win_count;
    }

    public void setAi_win_count(int ai_win_count) throws SQLException {
        SnakeGame.database.updateConfig("ai_win_count", ai_win_count);
        this.ai_win_count = ai_win_count;
    }

    public int getPlayer_win_count() {
        return player_win_count;
    }

    public void setPlayer_win_count(int player_win_count) throws SQLException {
        SnakeGame.database.updateConfig("player_win_count", player_win_count);
        this.player_win_count = player_win_count;
    }

    public boolean isSfx_on() {
        return sfx_on;
    }

    public void setSfx_on(boolean sfx_on) throws SQLException {
        SnakeGame.database.updateConfig("sfx_on", sfx_on ? 1 : 0);
        this.sfx_on = sfx_on;
    }

    public boolean isBg_on() {
        return bg_on;
    }

    public void setBg_on(boolean bg_on) throws SQLException {
        SnakeGame.database.updateConfig("bg_on", bg_on ? 1 : 0);
        this.bg_on = bg_on;
    }

    @Override
    public String toString() {
        return "GameConfig [high_score=" + high_score + ", speed=" + speed + ", has_obstacle=" + has_obstacle
                + ", ai_win_count=" + ai_win_count + ", player_win_count=" + player_win_count + ", sfx_on=" + sfx_on
                + ", bg_on=" + bg_on + "]";
    }

}
