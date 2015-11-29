package test;

import org.javalite.activejdbc.Base;

import java.sql.SQLException;

/**
 * Created by Alberto on 28/11/2015.
 */
public class Database {


    public static void dropAndCreate() throws SQLException, ClassNotFoundException {

        Base.open("org.sqlite.JDBC", "jdbc:sqlite:database.db", "", "");

        Base.exec("DROP TABLE IF EXISTS movies");
        Base.exec("DROP TABLE IF EXISTS actors");
        Base.exec("DROP TABLE IF EXISTS movies_actors");
        Base.exec("DROP TABLE IF EXISTS reviews");

        Base.exec("CREATE TABLE movies (" +
                "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT " +
                ")"
        );
        Base.exec("CREATE TABLE actors (" +
                "id   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT " +
                ")"
        );
        Base.exec("CREATE TABLE movies_actors (\n" +
                "id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "movie_id INTEGER NOT NULL REFERENCES movies(id), " +
                "actor_id INTEGER NOT NULL REFERENCES actors(id) " +
                ")"
        );
        Base.exec("CREATE TABLE reviews (\n" +
                "id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "content  TEXT," +
                "movie_id INTEGER REFERENCES movies(id) " +
                ")"
        );
        Base.close();
    }
}
