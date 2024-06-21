package hadar_and_neta;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
            return price;
        }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " Name = '" + this.name + '\'' +
                ", Price = " + this.price;
    }
}
