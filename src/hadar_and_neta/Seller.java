package hadar_and_neta;

public class Seller {
    private User user;
    private ProductList productList;

    public Seller(User user) {
        this.user = new User(user);
        this.productList = new ProductList();
    }

    public User getUser() {
        return this.user;
    }

    public ProductList getProductList() {
        return this.productList;
    }

    public void setUser(User user) {
        this.user = new User(user);
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
