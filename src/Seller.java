import java.util.Arrays;

public class Seller {
    private Username user;
    private Product[] allProducts;
    private int productSize = 0;

    public Seller(Username user) {
        this.user = new Username(user);
        this.allProducts = new Product[2];
    }

    public Username getUser() {
        return user;
    }
    public void setUser(Username user) {
        this.user = user;
    }

    private void increaseProductArray() {
        // increase array size by 2 and return new array
        Product[] tempArray = new Product[this.allProducts.length *2];
        for (int i = 0; i < this.productSize; i++) {
            tempArray[i] = this.allProducts[i];
        }
        this.allProducts = tempArray;
    }
    public void addProduct(Product product) {
        if (this.productSize == this.allProducts.length) {
            increaseProductArray();
        }
        this.allProducts[this.productSize] = product;
        this.productSize ++;
    }

    public String printProducts() {
        String productsStr = "";
        for (int i = 0; i < this.productSize; i++) {
            productsStr += (i+1) + "." + this.allProducts[i].toString();
        }
        return productsStr;
    }

    public Product getProductByIndex(int index) {
        if (index < 0 || index >= this.productSize) {
            return null;
        }
        return this.allProducts[index];
    }

    public int getProductSize() {
        return this.productSize;
    }

    @Override
    public String toString() {
        return "{" +
                "user='" + this.user.toString() + '\'' +
                ", products=" + printProducts() +
                '}';
    }
}
