public class Buyer {
    private Username user;
    private Address address;
    private Cart currentCart;
    private Cart[] cartHistory;
    private int cartHistorySize = 0;

    public Buyer(Username user, Address address) {
        this.user = new Username(user);
        this.address = new Address(address);
        this.cartHistory = new Cart[2];
        this.currentCart = new Cart();
    }
    public Username getUser() {
        return user;
    }

    public void updateCart(Product product) {
        this.currentCart.addProductToCart(product);
    }

    @Override
    public String toString() {
        String cartHistoryStr = "";
        for (int i = 0; i < this.cartHistorySize; i++) {
            cartHistoryStr += (i+1) + "." + this.cartHistory[i].toString();
        }
        return "{" +
                "user='" + this.user.toString() + '\'' +
                ", current cart=" + this.currentCart.toString() +
                ", history=" + cartHistoryStr + '}';
    }
}
