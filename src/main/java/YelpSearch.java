import kong.unirest.Unirest;

import java.util.Map;

import static input.InputUtils.stringInput;

public class YelpSearch {

    public static void main(String[] args) {

        String url = "https://api.yelp.com/v3/businesses/search";
        String apiKey = System.getenv("YELP_API_KEY");

        String restaurantType = stringInput("What type of restaurant would you like to search for?");

        Map<String, Object> query = Map.of(
                "term", restaurantType, // type of restaurant
                "location", "Minneapolis, MN",
                "categories", "restaurants",
                "price", "1"); // lowest price

        YelpResponse response = Unirest
                .get(url)
                .header("Authorization", "Bearer " + apiKey)
                .queryString(query).
                asObject(YelpResponse.class).getBody();

//        System.out.println(response);

            for (Business b : response.businesses) {
                System.out.println(b.name);
                System.out.println("Rating: " + b.rating);
                System.out.println(b.location.address1 + ", " + b.location.city);

            }

    }
}
