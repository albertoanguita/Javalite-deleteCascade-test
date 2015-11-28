package test;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 * Created by Alberto on 28/11/2015.
 */
@Table("movies_actors")
public class MoviesActors extends Model {

    @Override
    public void beforeDelete() {
        System.out.println("Movie to Actor '" + get("id") + "' deleted!!!");
    }

}
