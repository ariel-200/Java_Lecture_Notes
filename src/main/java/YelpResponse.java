// Yelp API response classes
public class YelpResponse {
    // field for businesses
    public Business[] businesses;

}

class Business {
    // represents one business object
    public String name;
    public double rating;
    public Location location;

}

class Location {
    public String city;
    public String address1;
}