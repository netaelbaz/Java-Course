package hadar_and_neta;

import java.util.Date;

public class Cart {
    private double price;
    private Product[] products;
    private Date date;
    private int productsAmount;

    public Cart() {
        this.price = 0;
        this.products = new Product[2];
        this.productsAmount = 0;
    }
    public Date getDate() {
        return date;
    }
    public Product[] getProducts() {
        return products;
    }
    public double getPrice() {
        return price;
    }
    public int getProductsAmount() {
        return productsAmount;
    }
    public void updateDate() {
        this.date = new Date();
    }

    private void increaseProductArray() {
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

    @Override
    public String toString() {
        String productsStr = "";
        for (int i = 0; i < this.productsAmount; i++) {
            String newLine = i == (this.productsAmount - 1) ? "" : "\n"; // add new line only if not last element
            productsStr += (i+1) + "." + this.products[i].toString() + newLine;
        }
        return "{" +
                "price = '" + this.price + '\'' +
                ", products = [" + productsStr + ']' +
                ", order date = " + this.date +
                '}';
    }
}
