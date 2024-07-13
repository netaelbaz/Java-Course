package hadar_and_neta;

public class Address {
    private String street;
    private String city;
    private String state;
    private int buildingNumber;

    public Address(String street, String city, String state, int buildingNumber)
            throws IllegalArgumentException {
        setStreet(street);
        setCity(city);
        setState(state);
        setBuildingNumber(buildingNumber);
    }

    public Address(Address other) throws IllegalArgumentException {
        setStreet(other.street);
        setCity(other.city);
        setState(other.state);
        setBuildingNumber(other.buildingNumber);
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
    public void setStreet(String street) throws IllegalArgumentException {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street name can't be empty");
        }
        this.street = street;
    }
    public void setCity(String city) throws IllegalArgumentException {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name can't be empty");
        }
        this.city = city;
    }
    public void setBuildingNumber(int buildingNumber) throws IllegalArgumentException {
        if (buildingNumber <=0 ) {
            throw new IllegalArgumentException("Building number must be greater than 0");
        }
        this.buildingNumber = buildingNumber;
    }
    public void setState(String state) throws IllegalArgumentException {
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State name can't be empty");
        }
        this.state = state;
    }

    @Override
    public String toString() {
        return  this.street + ' ' + this.buildingNumber + '\n'
                + this.city + ", " + this.state;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Address)) {
            return false;
        }
        Address otherAddress = (Address)other;
        return otherAddress.street.equals(this.street) && otherAddress.state.equals(this.state) &&
                otherAddress.city.equals(this.city) &&
                otherAddress.buildingNumber == this.buildingNumber;
    }
}
