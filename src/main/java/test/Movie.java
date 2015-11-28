package test;

import org.javalite.activejdbc.Model;

/**
 * Created by Alberto on 28/11/2015.
 */
public class Movie extends Model {

    @Override
    public void beforeDelete() {
        System.err.println("Movie '" + get("title") + "' deleted!!!");
    }

}
