import java.util.Arrays;

public class Seller {
    private Username user;
    private Product[] allProducts;
    private int productSize = 0;

    public Seller(Username user) {
        this.user = user;
        this.allProducts = new Product[0];
    }
    public Username getUser() {
        return user;
    }
    public void setUser(Username user) {
        this.user = user;
    }

    private  void increaseProductArray() {
        // increase array size by 2 and return new array
        int newArrayLength = (this.productSize == 0) ? 1 : this.productSize *2;
        Product[] tempArray = Arrays.copyOf(this.allProducts, newArrayLength);
        this.allProducts = tempArray;
    }
    public void addProduct(Product product) {
        if (this.productSize == this.allProducts.length) {
            increaseProductArray();
        }
        this.allProducts[this.productSize] = product;
        this.productSize ++;
    }
}
