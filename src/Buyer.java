public class Buyer {
    private Username user;
    private Address address;
    private Cart currentCart;
    private Cart[] cartHistory;
    private int cartHistorySize = 0;

    public Buyer(Username user, Address address) {
        this.user = user;
        this.address = address;
        this.cartHistory = new Cart[0];
        this.currentCart = new Cart();
    }
    public Username getUser() {
        return user;
    }
}
