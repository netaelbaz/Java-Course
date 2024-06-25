package hadar_and_neta;

import java.util.Date;

public class Cart {
    private double price;
    private ProductList productList;
    private Date date;

    public Cart() {
        this.price = 0;
        this.productList = new ProductList();
    }
    public Date getDate() {
        return date;
    }
    public ProductList getProductList() {
        return this.productList;
    }
    public double getPrice() {
        return price;
    }
    public void setDateToCurrent() {
        this.date = new Date();
    }
    public void addProductToCart(Product product) {
        this.productList.addProduct(product);
        this.price += product.getPrice();
    }

    @Override
    public String toString() {
        return  "Total Price: " + this.price + '\n' +
                "Products: " + this.productList.toString() + '\n' +
                "Order Date: " + (this.date != null ? this.date : "No order yet") ;
    }
}
