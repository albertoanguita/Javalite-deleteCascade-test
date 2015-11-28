# Javalite-deleteCascade-test

The test uses a very simple database with one one-to-many relation and one many-to-many relation. The database is SQLite.

There are 4 models:
* Movie: has a one-to-many relation with Review, and a many-to-many relation with Actor
* Actor
* MoviesActors: the associative table relating movies and actors
* Review

All models override the beforeDelete() method, printing on the screen the event.

Additional files:
* pom.xml: maven definitions
* Database.db: the sqlite database
* Database.java: script for creating the database
* Test.java: the test code. Run this class to execute the code. The test prints the results on System.out

In the test, we create two movies and relate each movie with one actor and one review. One movie is deleted with deleteCascade(), and the other with deleteCascadeShallow(). After each delete, the test prints which entities are frozen and which are not.

# Obtained output

## deleteCascade()

The callbacks of the movie, the related actor and the related review are triggered, but not the callback of the MoviesActors record.

Only the movie itself is frozen.


## deleteCascadeShallow()

Only the callback of the movie is triggered.

Only the movie itself is frozen.

