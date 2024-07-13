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


    public Product(String name, double price, Category category) throws IllegalArgumentException {
        setName(name);
        setPrice(price);
        setCategory(category);
        this.id = ++counter;
    }

    public Product(Product other) {
        setName(other.name);
        setPrice(other.price);
        setCategory(other.category);
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

    public void setCategory(Category newCategory) {
        this.category = newCategory;
    }
    public void setName(String name) throws IllegalArgumentException{
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.name = name;
    }
    public void setPrice(double price) {
        if (price == 0) {
            throw new IllegalArgumentException("Price can't be zero");
        }
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }
        Product otherProduct = (Product)other;
        return otherProduct.name.equals(this.name) && otherProduct.id == this.id &&
                otherProduct.price == this.price &&
                otherProduct.category.equals(this.category);
    }
}
