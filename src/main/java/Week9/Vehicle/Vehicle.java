package Week9.Vehicle;

public class Vehicle {

    private String name;
    private String make;
    private String model;
    private boolean isElectric;


    // Constructor
    public Vehicle(String name, String make, String model, boolean isElectric) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.isElectric = isElectric;
    }

    @Override
    public String toString() {
        String electric = isElectric ? "Electric" : "Gasoline";
        return String.format("%s: %s %s. %s", name, make, model, electric);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }
}
