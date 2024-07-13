package hadar_and_neta;

import java.util.Arrays;
import java.util.Date;

public class Cart implements Cloneable {
    private double price;
    private ProductList productList;
    private Date date;

    public Cart() {
        this.price = 0;
        this.productList = new ProductList();
    }
    public Date getDate() {
        return this.date;
    }
    public ProductList getProductList() {
        return this.productList;
    }
    public double getPrice() {
        return this.price;
    }
    public void setDateToCurrent() {
        this.date = new Date();
    }
    public void addProductToCart(Product product) {
        Product productToAdd = new Product(product);
        this.productList.addProduct(productToAdd);
        if (product instanceof SpecialProduct) {
            this.price += ((SpecialProduct) product).getPackagingPrice();
        }
        this.price += product.getPrice();
    }

    @Override
    public String toString() {
        return  "Total Price: " + this.price + '\n' +
                "Products: " + this.productList.toString() + '\n' +
                "Order Date: " + (this.date != null ? this.date : "No order yet") ;
    }

    @Override
    public Cart clone() throws CloneNotSupportedException {
        Cart temp = (Cart)super.clone();
        temp.productList = productList.clone();
        temp.date = null;
        return temp;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cart)) {
            return false;
        }
        Cart otherCart = (Cart)other;
        return otherCart.price == this.price && otherCart.date.equals(this.date) &&
                otherCart.productList.equals(this.productList);
    }
}
