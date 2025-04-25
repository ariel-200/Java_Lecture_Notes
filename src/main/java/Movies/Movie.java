package Movies;

public class Movie {

    private int id;
    private String name;
    private int stars;
    private boolean watched;

    // Constructor
    Movie(String name, int stars, boolean watched) {
        this.name = name;
        this.stars = stars;
        this.watched = watched;
    }

    // Constructor
    Movie(int id, String name, int stars, boolean watched) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.watched = watched;
    }

    // To String Method
    @Override
    public String toString() {
        return "ID " + id + " Movie name: " + name + " is rated " + stars + " stars. Have you watched this? " + watched;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
