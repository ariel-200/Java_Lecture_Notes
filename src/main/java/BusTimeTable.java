import kong.unirest.Unirest;

public class BusTimeTable {
    public static void main(String[] args) {

        String url = "https://svc.metrotransit.org/NexTrip/17940?format=json";
        MetroTransitResponse response = Unirest.get(url).asObject(MetroTransitResponse.class).getBody();
        Bus[] buses = response.departures;

        String template = "%-10s %-40s %-20s\n";

        // Table header
        System.out.printf(template, "Route", "Description", "Arrival Time");
        System.out.println("=".repeat(70));

        // Read information about each bus, display in table form
        for (Bus bus : buses) {
            System.out.printf("%-10s %-40s %-20s\n", bus.route_id, bus.description, bus.departure_text);
        }

    }
}

class MetroTransitResponse {
    Bus[] departures;
}

class Bus {
    public String departure_text;
    public String route_id;
    public String description;

}