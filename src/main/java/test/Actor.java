package test;

import org.javalite.activejdbc.Model;

/**
 * Created by Alberto on 28/11/2015.
 */
public class Actor extends Model {

    @Override
    public void beforeDelete() {
        System.out.println("Actor '" + get("name") + "' deleted!!!");
    }

}
