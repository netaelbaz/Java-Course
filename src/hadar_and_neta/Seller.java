package hadar_and_neta;

public class Seller implements Comparable<Seller>{
    private User user;
    private ProductList productList;

    public Seller(User user) throws IllegalArgumentException {
        this.user = new User(user);
        this.productList = new ProductList();
    }

    public User getUser() {
        return this.user;
    }

    public ProductList getProductList() {
        return this.productList;
    }

    public Product getProductByIndex(int index) {
        if (index < 0 || index >= this.productList.getProductsAmount()) {
            return null;
        }
        return this.productList.getAllProducts()[index];
    }

    @Override
    public int compareTo(Seller s) {
        if (productList.getProductsAmount() < s.getProductList().getProductsAmount()){
            return -1;
        }
        else if(productList.getProductsAmount() > s.getProductList().getProductsAmount()){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.user.getName() + "\n" +
                "Products: " + this.productList.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Seller)) {
            return false;
        }
        Seller otherSeller = (Seller)other;
        return otherSeller.user.equals(this.user) && otherSeller.productList.equals(this.productList);
    }
}
