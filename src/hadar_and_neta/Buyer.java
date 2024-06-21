package hadar_and_neta;

public class Buyer {
    private Username user;
    private Address address;
    private Cart currentCart;
    private Cart[] orderHistory;
    private int orderHistorySize = 0;

    public Buyer(Username user, Address address) {
        this.user = new Username(user);
        this.address = new Address(address);
        this.orderHistory = new Cart[2];
        this.currentCart = new Cart();
    }
    public Username getUser() {
        return user;
    }
    public Address getAddress() {
        return address;
    }
    public Cart getCurrentCart() {
        return currentCart;
    }
    public Cart[] getOrderHistory() {
        return orderHistory;
    }
    public int getOrderHistorySize() {
        return orderHistorySize;
    }
    private void increaseHistoryArray() {
        // increase array size by 2 and return new array
        Cart[] tempArray = new Cart[this.orderHistory.length *2];
        for (int i = 0; i < this.orderHistorySize; i++) {
            tempArray[i] = this.orderHistory[i];
        }
        this.orderHistory = tempArray;
    }
    public void pay() {
        this.currentCart.updateDate();
        if (this.orderHistorySize == this.orderHistory.length){
            increaseHistoryArray();
        }
        this.orderHistory[orderHistorySize] = currentCart;
        this.orderHistorySize++;
        this.currentCart = new Cart();
    }
    public void updateCart(Product product) {
        this.currentCart.addProductToCart(product);
    }

    @Override
    public String toString() {
        String cartHistoryStr = "";
        for (int i = 0; i < this.orderHistorySize; i++) {
            cartHistoryStr += (i+1) + "." + this.orderHistory[i].toString();
        }
        return "{ " +
                "User: " + this.user.toString() + '\n' +
                "Current Cart: " + this.currentCart.toString() + "\n" +
                "History: " + cartHistoryStr +
                " }";
    }
}
