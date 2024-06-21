package hadar_and_neta;

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
    }

    public int getSellersAmount() {
        return this.sellersAmount;
    }

    public int getBuyersAmount() {
        return this.buyersAmount;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public Buyer[] getBuyers() {
        return buyers;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public void addNewSeller(String username, String password) {
    Username user = new Username(username, password);

    if (this.sellersAmount == this.sellers.length) {
        increaseSellersArray();
    }
    this.sellers[this.sellersAmount] = new Seller(user);;
    this.sellersAmount ++;
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
        Buyer[] tempArray = new Buyer[this.buyers.length *2];
        for (int i = 0; i < this.buyersAmount; i++) {
            tempArray[i] = this.buyers[i];
        }
        this.buyers = tempArray;
    }

    public boolean findDuplicateSellerName(String username) {
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

    public boolean addProductToSeller(int sellerIndex, String productName, double productPrice) {
        if (!validateSellerIndex(sellerIndex)) {
            return false;
        }
        Product newProduct = new Product(productName, productPrice);
        this.sellers[sellerIndex].addProduct(newProduct);
        return true;
    }

    private boolean validateSellerIndex(int index) {
        return index >= 0 && index < this.sellersAmount;
    }

    public boolean validateBuyerIndex(int index) {
        return index >= 0 && index < this.buyersAmount;
    }

    public String getSellersName() {
        String sellerNames = "";
        for (int i = 0; i < this.sellersAmount; i++) {
            String newLine = i == (this.sellersAmount -1) ? "" : "\n"; // add new line only if not last element
            sellerNames += i+1 + ". " + this.sellers[i].getUser().getName() + newLine;
        }
        return sellerNames;
    }

    public String getBuyersName() {
        String buyerNames = "";
        for (int i = 0; i < this.buyersAmount; i++) {
            String newLine = i == (this.buyersAmount -1) ? "" : "\n"; // add new line only if not last element
            buyerNames += i+1 + ". " + this.buyers[i].getUser().getName() + newLine;
        }
        return buyerNames;
    }

    public String getProductsOfSeller(int sellerIndex) {
        if (validateSellerIndex(sellerIndex) && this.sellers[sellerIndex].getProductSize() != 0)  {
            return this.sellers[sellerIndex].getStrOfProducts();
        }
        return null;
    }

    public boolean addProductToCart(int productIndex, int buyerIndex, int sellerIndex) {
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

    public double getCartPriceByBuyer(int buyerIndex) {
        if (validateBuyerIndex(buyerIndex)) {
            return this.buyers[buyerIndex].getCurrentCart().getPrice();
        }
        return 0;
    }
}
