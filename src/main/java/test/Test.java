package test;

import org.javalite.activejdbc.Base;

/**
 * Created by Alberto on 28/11/2015.
 */
public class Test {

    public static void main(String[] args) {

        try {
            Database.dropAndCreate();
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:database.db", "", "");


            Movie movie1 = new Movie();
            movie1.set("title", "Rocky");
            movie1.saveIt();

            Movie movie2 = new Movie();
            movie2.set("title", "Alien");
            movie2.saveIt();

            Actor actor1 = new Actor();
            actor1.set("name", "Silvester Stallone");
            actor1.saveIt();
            Actor actor2 = new Actor();
            actor2.set("name", "Sigourney Weaver");
            actor2.saveIt();

            movie1.add(actor1);
            movie2.add(actor2);

            MoviesActors moviesActors1 = MoviesActors.findFirst("id = 1");
            MoviesActors moviesActors2 = MoviesActors.findFirst("id = 2");

            Review review1 = new Review();
            review1.set("content", "First review");
            Review review2 = new Review();
            review2.set("content", "Second review");

            movie1.add(review1);
            movie2.add(review2);

            System.err.println("Items are set up.");
            System.err.println("'Rocky' has a many to many relation with 'Silvester Stallone'.");
            System.err.println("'Rocky' has a one to many relation with First review.");
            System.err.println("'Alien' has a many to many relation with 'Sigourney Weaver'.");
            System.err.println("'Alien' has a one to many relation with 'Second review'.");
            System.err.println();

            System.err.println("Deleting Rocky in cascade...");
            System.err.println("----------------------------");
            movie1.deleteCascade();
            System.err.println("Checking what items are frozen...");
            System.err.println("Rocky is frozen: " + movie1.isFrozen());
            System.err.println("Silvester Stallone is frozen: " + actor1.isFrozen());
            System.err.println("Rocky to Silvester Stallone is frozen: " + moviesActors1.isFrozen());
            System.err.println("Review for Rocky is frozen: " + review1.isFrozen());
            System.err.println("--------------------------------------------------------");
            System.err.println();

            System.err.println("Deleting Alien in cascade shallow...");
            System.err.println("------------------------------------");
            movie2.deleteCascadeShallow();
            System.err.println("Checking what items are frozen...");
            System.err.println("Alien is frozen: " + movie2.isFrozen());
            System.err.println("Sigourney Weaver is frozen: " + actor2.isFrozen());
            System.err.println("Alien to Sigourney Weaver is frozen: " + moviesActors2.isFrozen());
            System.err.println("Review for Alien is frozen: " + review2.isFrozen());
            System.err.println("--------------------------------------------------------");
            System.err.println();

            Base.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
