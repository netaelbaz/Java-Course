package hadar_and_neta;

public class Seller {
    private Username user;
    private ProductList productList;

    public Seller(Username user) {
        this.user = new Username(user);
        this.productList = new ProductList();
    }

    public Username getUser() {
        return this.user;
    }

    public ProductList getProductList() {
        return this.productList;
    }

    public void setUser(Username user) {
        this.user = new Username(user);
    }

    public Product getProductByIndex(int index) {
        if (index < 0 || index >= this.productList.getProductAmount()) {
            return null;
        }
        return this.productList.getAllProducts()[index];
    }

    @Override
    public String toString() {
        return this.user.getName() + "\n" +
                "Products: " + this.productList.toString();
    }
}
