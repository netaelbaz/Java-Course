import java.util.Arrays;
import java.util.Date;

public class Cart {
    private double price;
    private Product[] products;
    private Date date;
    private int productsAmount;

    public Cart() {
        this.price = 0;
        this.products = new Product[0];
        this.productsAmount = 0;
    }
    public double getPrice() {
        return price;
    }
    public void resetPrice(double price) {
        this.price = 0;
    }
    public Product[] getProducts() {
        return products;
    }

    private void increaseProductArray() {
        // increase array size by 2 and return new array
        int newArrayLength = (this.productsAmount == 0) ? 1 : this.productsAmount *2;
        Product[] tempArray = Arrays.copyOf(this.products, newArrayLength);
        this.products = tempArray;
    }

    public void addProductToCart(Product product) {
        if (this.productsAmount == this.products.length) {
            increaseProductArray();
        }
        this.products[this.productsAmount] = product;
        this.productsAmount ++;
        this.price += product.getPrice();
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String productsStr = "";
        for (int i = 0; i < this.productsAmount; i++) {
            productsStr += (i+1) + "." + this.products[i].toString();
        }
        return "{" +
                "price='" + this.price + '\'' +
                ", products=" + productsStr +
                '}';
    }
}
