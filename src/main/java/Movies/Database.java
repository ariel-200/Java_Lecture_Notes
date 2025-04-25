package Movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databasePath;

    // Constructor
    Database(String databasePath) {

        this.databasePath = databasePath;

        try (Connection con = DriverManager.getConnection(databasePath);
        Statement stmt = con.createStatement()){

            // Create Table
            stmt.execute("CREATE TABLE IF NOT EXISTS movies (" +
                    "id integer primary key, " +
                    "name text UNIQUE CHECK(length(name) >= 1), " +
                    "stars integer CHECK(stars >= 0 AND stars <=5), " +
                    "watched boolean)");

        // Error Handling
        } catch (SQLException e) {
            System.out.println("Error creating movie DB table because " + e);
        }
    }

    // Method to add movie to database
    public void addNewMovie(Movie movie) {

        String insert = "INSERT INTO movies (name, stars, watched) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(databasePath);
             PreparedStatement stmt = con.prepareStatement(insert)){

            // set the movies values
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getStars());
            stmt.setBoolean(3, movie.isWatched());
            // Execute
            stmt.execute();

        // Error Handling
        } catch (SQLException e) {
            System.out.println("Error adding movie " + movie + " because " + e);
        }
    }

    // Method to get a list of all movies
    public List<Movie> getAllMovies() {

        try (Connection con = DriverManager.getConnection(databasePath);
        Statement stmt = con.createStatement()) {

            // Get all movies
            ResultSet allMovies = stmt.executeQuery("SELECT * FROM movies ORDER BY name");
            // Create a new list for the movies
            List<Movie> movies = new ArrayList<>();

            // Loop over the movies
            while (allMovies.next()) {
                // Create variables for the data
                int id = allMovies.getInt("id");
                String name = allMovies.getString("name");
                int stars = allMovies.getInt("stars");
                boolean watched = allMovies.getBoolean("watched");

                // Create new Movie object
                Movie movie = new Movie(id, name, stars, watched);
                // Add the Movie to the list
                movies.add(movie);
            }

            // Return movie list
            return movies;

        // Error Handling
        } catch (SQLException e) {
            System.out.println("Error getting all movies because " + e);
            return null;
        }
    }

    public List<Movie> getWatchedMovies(boolean watchedStatus) {

        try (Connection con = DriverManager.getConnection(databasePath);
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM movies WHERE watched = ?")) {

            // Set and execute statement
            stmt.setBoolean(1, watchedStatus);
            ResultSet unwatchedMovies = stmt.executeQuery();

            // Create new list for unwatched movies
            List<Movie> movies = new ArrayList<>();

            // Loop over the unwatched movies
            while (unwatchedMovies.next()) {
                // Create variables for the data
                int id = unwatchedMovies.getInt("id");
                String name = unwatchedMovies.getString("name");
                int stars = unwatchedMovies.getInt("stars");
                boolean watched = unwatchedMovies.getBoolean("watched");

                // Create new Movie object
                Movie movie = new Movie(id, name, stars, watched);
                // Add movie to the list
                movies.add(movie);
            }

            return movies;

        // Error Handling
        } catch (SQLException e){
            System.out.println("Error getting watched movies because " + e);
            return null;
        }
    }

    public void updateMovie(Movie movie) {

        String sql = "UPDATE movies SET stars = ?, watched = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(databasePath);
        PreparedStatement stmt = con.prepareStatement(sql)){

            // Set Variables and execute statement
            stmt.setInt(1, movie.getStars());
            stmt.setBoolean(2, movie.isWatched());
            stmt.setInt(3, movie.getId());
            stmt.execute();

        // Error Handling
        } catch (SQLException e) {
            System.out.println("Error updating movie " + movie + " because " + e);
        }
    }

    // Method to delete Movies
    public void deleteMovie(Movie movie) {

        try (Connection con = DriverManager.getConnection(databasePath);
        PreparedStatement stmt = con.prepareStatement("DELETE FROM movies WHERE id = ?")) {

            // Set variables and execute statement
            stmt.setInt(1, movie.getId());
            stmt.execute();

        // Error Handling
        } catch (SQLException e) {
            System.out.println("Error deleting movie " + movie + " because " + e);
        }
    }

    public List<Movie> search(String search) {

        String sql = "SELECT * FROM movies WHERE upper(name) LIKE upper(?)";

        try (Connection con = DriverManager.getConnection(databasePath);
        PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set variables and execute statement
            stmt.setString(1, "%" + search + "%");
            ResultSet results = stmt.executeQuery();

            // Create new list of movies
            List<Movie> movies = new ArrayList<>();

            // Loop over the results
            while (results.next()) {
                // Create variables for the data
                int id = results.getInt("id");
                String name = results.getString("name");
                int stars = results.getInt("stars");
                boolean watched = results.getBoolean("watched");

                // Create new Movie object
                Movie movie = new Movie(id, name, stars, watched);
                // Add movie to the list
                movies.add(movie);
            }

            return movies;

        // Error Handling
        } catch (SQLException e) {
            System.out.println("Error searching movies because " + e);
            return null;
        }

    }

}
