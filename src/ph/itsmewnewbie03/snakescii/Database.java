package ph.itsmewnewbie03.snakescii;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
/**
 * Class for Database Manipulation
 * @author <a href="https://github.com/itsmenewbie03">@itsmenewbie03</a>
 */
public class Database {
    Connection connection;
    Statement statement;
    private String[] keys = { "speed", "high_score", "has_obstacle", "player_win_count", "ai_win_count","sfx_on","bg_on" };

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:game.db");
        this.statement = connection.createStatement();
    }

    public void setDefaultConfig() throws SQLException {
        if(isConfigured()){
            System.out.println("db already configured!");
            return;
        }
        statement.executeUpdate("create table CONFIG(id integer,key string,value integer)");
        statement.executeUpdate("insert into CONFIG values(0,'speed',1)");
        statement.executeUpdate("insert into CONFIG values(1,'high_score',0)");
        statement.executeUpdate("insert into CONFIG values(2,'has_obstacle',0)");
        statement.executeUpdate("insert into CONFIG values(3,'player_win_count',0)");
        statement.executeUpdate("insert into CONFIG values(4,'ai_win_count',0)");
        statement.executeUpdate("insert into CONFIG values(5,'sfx_on',0)");
        statement.executeUpdate("insert into CONFIG values(6,'bg_on',0)");
    }
    public boolean isConfigured() throws SQLException{
        return statement.executeQuery("SELECT name FROM sqlite_schema WHERE type='table' AND name='CONFIG'").next();
    }
    public void updateConfig(String key, int value) throws SQLException {
        int id = Arrays.asList(keys).indexOf(key);
        if (id == -1) {
            System.out.println("update rejected invalid key!");
            return;
        }
        statement.executeUpdate(String.format("UPDATE CONFIG SET value=%d WHERE id=%d", value, id));
    }

    public ResultSet getConfig() throws SQLException {
        return statement.executeQuery("select * from CONFIG");
    }

}
