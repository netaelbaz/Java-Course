import java.util.Date;

public class Cart {
    private double price;
    private Product[] products;
    private Date date; // move to order class
    private int productsAmount;

    public Cart() {
        this.price = 0;
        this.products = new Product[2];
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
        Product[] tempArray = new Product[this.products.length *2];
        for (int i = 0; i < this.productsAmount; i++) {
            tempArray[i] = this.products[i];
        }
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
