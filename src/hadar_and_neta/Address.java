package hadar_and_neta;

public class Address {
    private String street;
    private String city;
    private String state;
    private int buildingNumber;

    public Address(String street, String city, String state, int buildingNumber) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.buildingNumber = buildingNumber;
    }

    public Address(Address other) {
        this.street = other.street;
        this.city = other.city;
        this.state = other.state;
        this.buildingNumber = other.buildingNumber;
    }

    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public int getBuildingNumber() {
        return buildingNumber;
    }
}
