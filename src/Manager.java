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
        Username user = new Username(username, password);
        Buyer newBuyer = new Buyer(user, address);

        if (this.buyersAmount == this.buyers.length) {
            increaseBuyersArray();
        }
        this.buyers[this.buyersAmount] = newBuyer;
        this.buyersAmount ++;
    }

    private  void increaseSellersArray() {
        // increase array size by 2 and return new array
        int newArrayLength = (this.sellersAmount == 0) ? 1 : this.sellersAmount *2;
        Seller[] tempArray = new Seller[newArrayLength];
        for (int i = 0; i < this.sellersAmount; i++) {
            tempArray[i] = this.sellers[i];
        }
        this.sellers = tempArray;
    }

    private  void increaseBuyersArray() {
        // increase array size by 2 and return new array
        int newArrayLength = (this.buyersAmount == 0) ? 1 : this.buyersAmount *2;
        Buyer[] tempArray = new Buyer[newArrayLength];
        for (int i = 0; i < this.buyersAmount; i++) {
            tempArray[i] = this.buyers[i];
        }
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

    public void addProductToSeller(int sellerIndex, String productName, double productPrice) {
        Product newProduct = new Product(productName, productPrice);
        this.sellers[sellerIndex-1].addProduct(newProduct);
    }

    public int getSellersAmount() {
        return this.sellersAmount;
    }

    public int getBuyersAmount() {
        return this.buyersAmount;
    }

    public void printSellersName() {
        for (int i = 0; i < this.sellersAmount; i++) {
            System.out.println(i+1 + ". " + this.sellers[i].getUser().getName());
        }
    }

    public void printBuyersName() {
        for (int i = 0; i < this.buyersAmount; i++) {
            System.out.println(i+1 + ". " + this.buyers[i].getUser().getName());
        }
    }

    public void printProductsOfSeller(int sellerIndex) {
        System.out.println(this.sellers[sellerIndex-1].printProducts());
    }

    public void addProductToCart(int productIndex, int buyerIndex, int sellerIndex) {
        Product requestedProduct = this.sellers[sellerIndex].getProductByIndex(productIndex);
        Product productToCart = new Product(requestedProduct);
        this.buyers[buyerIndex].updateCart(productToCart);

    }

    public void sellerToString() {
        for (Seller seller : this.sellers) {
            System.out.println(seller.toString());
        }
    }

    public void buyerToString() {
        for (Buyer buyer : this.buyers) {
            System.out.println(buyer.toString());
        }
    }

}
