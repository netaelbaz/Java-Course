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
        return this.street;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public int getBuildingNumber() {
        return this.buildingNumber;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return  this.street + ' ' + this.buildingNumber + '\n'
                + this.city + ", " + this.state;
    }
}
