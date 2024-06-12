public class Username {
    private String name;
    private String password;
    private Address address;

    public Username(String name, String password, Address address) {
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public Username(String name, String password) {
        this.name = name;
        this.password = password;
        this.address = null; // check
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public Address getAddress() {
        return address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

}
