import java.util.Arrays;

public class Manager {
    private Seller[] sellers;
    private Buyer[] buyers;
    private int sellersAmount = 0;
    private int buyersAmount = 0;

    public Manager() {
        this.sellers = new Seller[0];
        this.buyers = new Buyer[0];
    }

    public void addNewSeller(String username, String password) {
    Username user = new Username(username, password);
    Seller newSeller = new Seller(user);

    if (this.sellersAmount == this.sellers.length) {
        increaseSellersArray();
    }
    this.sellers[this.sellersAmount] = newSeller;
        this.sellersAmount ++;
    }

    public Address createAddress(String street, String city, String state, int buildingNumber) {
        return new Address(street, city, state, buildingNumber);
    }

    public void addNewBuyer(String username, String password, Address address) {
        Username user = new Username(username, password, address);
        Buyer newBuyer = new Buyer(user);

        if (this.buyersAmount == this.buyers.length) {
            increaseBuyersArray();
        }
        this.buyers[this.buyersAmount] = newBuyer;
        this.buyersAmount ++;
    }

    private  void increaseSellersArray() {
        // increase array size by 2 and return new array
        int newArrayLength = (this.sellersAmount == 0) ? 1 : this.sellersAmount *2;
        Seller[] tempArray = Arrays.copyOf(this.sellers, newArrayLength);
        this.sellers = tempArray;
    }

    private  void increaseBuyersArray() {
        // increase array size by 2 and return new array
        int newArrayLength = (this.buyersAmount == 0) ? 1 : this.buyersAmount *2;
        Buyer[] tempArray = Arrays.copyOf(this.buyers, newArrayLength);
        this.buyers = tempArray;
    }

    public boolean findDuplicateSellerName(String username) {
        // return false/true if duplicate found
        for (Seller seller : this.sellers) {
            if (seller != null && seller.getUser().getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean findDuplicateBuyerName(String username) {
        // return false/true if duplicate found
        for (Buyer buyer : this.buyers) {
            if (buyer != null && buyer.getUser().getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void addProductToSeller(int sellerIndex,String productName,double productPrice) {
        this.sellers[sellerIndex].addProduct(new Product(productName, productPrice));
    }
}
