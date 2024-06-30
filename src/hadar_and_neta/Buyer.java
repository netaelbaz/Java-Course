package hadar_and_neta;

public class Buyer {
    private Username user;
    private Address address;
    private Cart currentCart;
    private Cart[] orderHistory;
    private int orderHistorySize;

    public Buyer(Username user, Address address) {
        this.user = new Username(user);
        this.address = new Address(address);
        this.orderHistory = new Cart[2];
        this.currentCart = new Cart();
        this.orderHistorySize = 0;
    }

    public Username getUser() {
        return this.user;
    }
    public void setUser(Username user) {
        this.user = new Username(user);
    }
    public Address getAddress() {
        return this.address;
    }
    public void setAddress(Address address) {
        this.address = new Address(address);
    }
    public Cart getCurrentCart() {
        return this.currentCart;
    }
    public Cart[] getOrderHistory() {
        return this.orderHistory;
    }
    public int getOrderHistorySize() {
        return this.orderHistorySize;
    }
    public void pay() {
        this.currentCart.setDateToCurrent();
        if (this.orderHistorySize == this.orderHistory.length){
            increaseHistoryArray();
        }
        this.orderHistory[orderHistorySize] = currentCart;
        this.orderHistorySize++;
        this.currentCart = new Cart();
    }

    private void increaseHistoryArray() {
        Cart[] tempArray = new Cart[this.orderHistory.length *2];
        for (int i = 0; i < this.orderHistorySize; i++) {
            tempArray[i] = this.orderHistory[i];
        }
        this.orderHistory = tempArray;
    }

    @Override
    public String toString() {
        StringBuilder cartHistoryStr = new StringBuilder();
        if  ( this.orderHistorySize == 0 ) {
            cartHistoryStr.append("No orders history");
        }
        for (int i = 0; i < this.orderHistorySize; i++) {
            cartHistoryStr.append("\n" + (i+1) + ") " + this.orderHistory[i].toString());
        }
        return this.user.getName() + '\n' +
                "Current Cart: " + '\n' + this.currentCart.toString() + "\n" +
                "History: " + cartHistoryStr.toString();
    }
}
