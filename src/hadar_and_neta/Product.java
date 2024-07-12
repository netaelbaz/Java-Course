package hadar_and_neta;

public class Product implements Cloneable {

    public enum Category {
            KIDS,
            ELECTRICITY,
            OFFICE,
            CLOTHING,
    };
    private Category category;
    private String name;
    private double price;
    private static int counter;
    private int id;


    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.id = ++counter;
    }

    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
        this.id = ++counter;
    }

    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return category;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name = " + this.name +
                ", Price = " + this.price +
                ", Category = " + this.category;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
