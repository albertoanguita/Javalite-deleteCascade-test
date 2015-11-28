package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alberto on 28/11/2015.
 */
public class Database {


    public static void dropAndCreate() throws SQLException, ClassNotFoundException {
        dropDatabase("database.db");
        createDatabase("database.db");
    }


    private static void dropDatabase(String path) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path);

        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS movies");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS actors");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS movies_actors");
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS reviews");

        connection.close();
    }

    private static void createDatabase(String path) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path);

        connection.createStatement().executeUpdate(
                "CREATE TABLE movies (" +
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "title TEXT " +
                        ")"
        );
        connection.createStatement().executeUpdate(
                "CREATE TABLE actors (" +
                        "id   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT " +
                        ")"
        );
        connection.createStatement().executeUpdate(
                "CREATE TABLE movies_actors (\n" +
                        "id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "movie_id INTEGER NOT NULL REFERENCES movies(id), " +
                        "actor_id INTEGER NOT NULL REFERENCES actors(id) " +
                        ")"
        );
        connection.createStatement().executeUpdate(
                "CREATE TABLE reviews (\n" +
                        "id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "content  TEXT," +
                        "movie_id INTEGER REFERENCES movies(id) " +
                        ")"
        );
        connection.close();
    }


//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        dropAndCreate();
//    }

}
