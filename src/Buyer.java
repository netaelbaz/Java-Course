public class Buyer {
    private Username user;
    private Cart currentCart;
    private Cart[] cartHistory;

    public Buyer(Username user) {
        this.user = user;
        this.cartHistory = new Cart[0];
    }
    public Username getUser() {
        return user;
    }
}
