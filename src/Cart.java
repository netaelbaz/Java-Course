import java.util.Date;

public class Cart {
    private double price;
    private Product[] products;
    private Date date;

    public Cart(double price, Product[] products, Date date) {
        this.price = price;
        this.products = products;
        this.date = date;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Product[] getProducts() {
        return products;
    }
    public void setProducts(Product[] products) {
        this.products = products;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
