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
            review1.saveIt();
            Review review2 = new Review();
            review2.set("content", "Second review");
            review2.saveIt();

            movie1.add(review1);
            movie2.add(review2);

            System.out.println("Items are set up.");
            System.out.println("'Rocky' has a many to many relation with 'Silvester Stallone'.");
            System.out.println("'Rocky' has a one to many relation with First review.");
            System.out.println("'Alien' has a many to many relation with 'Sigourney Weaver'.");
            System.out.println("'Alien' has a one to many relation with 'Second review'.");
            System.out.println();

            System.out.println("Deleting Rocky in cascade...");
            System.out.println("----------------------------");
            movie1.deleteCascade();
            System.out.println("Checking what items are frozen...");
            System.out.println("Rocky is frozen: " + movie1.isFrozen());
            System.out.println("Silvester Stallone is frozen: " + actor1.isFrozen());
            System.out.println("Rocky to Silvester Stallone is frozen: " + moviesActors1.isFrozen());
            System.out.println("Review for Rocky is frozen: " + review1.isFrozen());
            System.out.println("--------------------------------------------------------");
            System.out.println();

            System.out.println("Deleting Alien in cascade shallow...");
            System.out.println("------------------------------------");
            movie2.deleteCascadeShallow();
            System.out.println("Checking what items are frozen...");
            System.out.println("Alien is frozen: " + movie2.isFrozen());
            System.out.println("Sigourney Weaver is frozen: " + actor2.isFrozen());
            System.out.println("Alien to Sigourney Weaver is frozen: " + moviesActors2.isFrozen());
            System.out.println("Review for Alien is frozen: " + review2.isFrozen());
            System.out.println("--------------------------------------------------------");
            System.out.println();

            Base.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
