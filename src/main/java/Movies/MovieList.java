package Movies;

import java.util.List;

import static input.InputUtils.*;

public class MovieList {

    private static final String DB_PATH = "jdbc:sqlite:movie_watchlist.sqlite";
    private static Database database;

    public static void main(String[] args) {

        // Create new database
        database = new Database(DB_PATH);
        // Add new movies
        addNewMovies();
        unwatchedAndRate();
        deleteWatchedMovies();
        searchMovie();
        System.out.println(); // blank line
        displayMovies();
    }

    // Method to get Rating 0-5
    public static int getRating() {
        int rating = positiveIntInput("What is your rating, in stars out of 5?");
        while (rating < 0 || rating > 5) {
            System.out.println("Error, enter a number between 0 and 5");
            rating = positiveIntInput("What is your rating, in stars out of 5?");
        }
        return rating;
    }

    public static String getNotNullName(){
        String name = stringInput("Enter the movie name");
        while (name.isEmpty()) {
            System.out.println("Error, enter a name");
            name = stringInput("Enter the movie name");
        }
        return name;
    }

    // Method to add new movies
    public static void addNewMovies() {
        // Loop to keep adding movies until the user stops
        do {
            // Get user input
            String name = getNotNullName();
            boolean watched = yesNoInput("Have you seen this movie yet?");
            int stars = 0;
            if (watched) {
                stars = getRating();
            }
            // Create new Movie object
            Movie movie = new Movie(name, stars, watched);
            // Add the Movie to the database
            database.addNewMovie(movie);

        } while (yesNoInput("Add a movie to the watchlist? "));
    }

    public static void displayMovies() {
        // Get list of movies
        List<Movie> movies = database.getAllMovies();
        // If list is empty print a message
        if (movies.isEmpty()) {
            System.out.println("There are no movies");
        // Else print out the list of movies
        } else {
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    public static void unwatchedAndRate() {
        // Get all unwatched movies
        List<Movie> unwatchedMovies = database.getWatchedMovies(false);

        // Loop over unwatched movies
        for (Movie movie : unwatchedMovies) {
            // Ask if they watched the movie yet
            boolean hasWatched = yesNoInput("Have you watched " + movie.getName() + " yet?");
            // If yes, ask for the rating and update the movie and database
            if (hasWatched) {
                int stars = positiveIntInput("What is your rating for " + movie.getName() + ", in stars out of 5?");
                movie.setWatched(true);
                movie.setStars(stars);
                database.updateMovie(movie);
            }
        }
    }

    // Method to delete watched movies
    public static void deleteWatchedMovies() {
        // Get all watched movies
        List<Movie> watchedMovies = database.getWatchedMovies(true);

        // Loop over watched movies
        for (Movie movie : watchedMovies) {
            // Ask if they want to delete the movie
            boolean delete = yesNoInput("Do you want to delete " + movie.getName() + "?");
            // Delete the movie if the user says yes
            if (delete) {
                database.deleteMovie(movie);
            }
        }
    }

    // Method to search for movies
    public static void searchMovie() {
        // Get the name to search from the user
        String search = stringInput("Enter the movie name you want to search");
        // Create a list of movies that match the search
        List<Movie> matches = database.search(search);

        // If there are no matching movies print a message
        if (matches.isEmpty()) {
            System.out.println("There are no movies that match " + search);
        // Print the matching movies
        } else {
            for (Movie movie : matches) {
                System.out.println(movie);
            }
        }
    }

}
