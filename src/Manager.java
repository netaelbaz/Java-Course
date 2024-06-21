import java.util.Arrays;

public class Manager {
    private String systemName;

    private Seller[] sellers;
    private int sellersAmount = 0;

    private Buyer[] buyers;
    private int buyersAmount = 0;

    public Manager(String systemName) {
        this.sellers = new Seller[2];
        this.buyers = new Buyer[2];
        this.systemName = systemName;
        // מה עושים עם הsystem name
    }

    public void addNewSeller(String username, String password) {
    Username user = new Username(username, password);

    if (this.sellersAmount == this.sellers.length) {
        increaseSellersArray();
    }
    this.sellers[this.sellersAmount] = new Seller(user);;
    this.sellersAmount ++;
    System.out.println("Successfully added new seller");
    }

    public Address createAddress(String street, String city, String state, int buildingNumber) {
        return new Address(street, city, state, buildingNumber);
    }

    public void addNewBuyer(String username, String password, Address address) {
        Username user = new Username(username, password);

        if (this.buyersAmount == this.buyers.length) {
            increaseBuyersArray();
        }
        this.buyers[this.buyersAmount] = new Buyer(user, address);;
        this.buyersAmount ++;
    }

    private  void increaseSellersArray() {
        // increase array size by 2 and return new array
        Seller[] tempArray = new Seller[this.sellers.length *2];
        for (int i = 0; i < this.sellersAmount; i++) {
            tempArray[i] = this.sellers[i];
        }
        this.sellers = tempArray;
    }

    private  void increaseBuyersArray() {
        // increase array size by 2 and return new array
        Buyer[] tempArray = new Buyer[this.buyers.length *2];
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
        // call this from the manager and not from the main
        for (Buyer buyer : this.buyers) {
            if (buyer != null && buyer.getUser().getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void addProductToSeller(int sellerIndex, String productName, double productPrice) {
        // do all the validations here and return false if could not add product
        Product newProduct = new Product(productName, productPrice);
        this.sellers[sellerIndex-1].addProduct(newProduct);
    }

    private boolean validateSellerIndex(int index) {
        return index >= 0 && index < this.sellersAmount;
    }

    private boolean validateBuyerIndex(int index) {
        return index >= 0 && index < this.buyersAmount;
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

    public boolean printProductsOfSeller(int sellerIndex) {
        if (!validateSellerIndex(sellerIndex)) {
            return false;
        }
        if (this.sellers[sellerIndex-1].getProductSize() == 0) {
            return false;
        }
        System.out.println(this.sellers[sellerIndex-1].printProducts()); // think about what to do with it
        return true;
    }

    public boolean addProductToCart(int productIndex, int buyerIndex, int sellerIndex) {
        // do all the validations here and return false if could not add product
        if (! validateBuyerIndex(buyerIndex)) {
            return false;
        }
        Product requestedProduct = this.sellers[sellerIndex].getProductByIndex(productIndex);
        if ( requestedProduct == null ) {
            return false;
        }
        Product productToCart = new Product(requestedProduct);
        this.buyers[buyerIndex].updateCart(productToCart);
        return true;
    }

    public void sellerToString() {
        for (int i = 0; i < this.sellersAmount; i++) {
            System.out.println(this.sellers[i].toString());
        }
    }

    public void buyerToString() {
        for (int i = 0; i < this.buyersAmount; i++) {
            System.out.println(this.buyers[i].toString());
        }
    }

}
