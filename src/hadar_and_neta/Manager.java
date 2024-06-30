package hadar_and_neta;

public class Manager {
    private String systemName;

    private Seller[] sellers;
    private int sellersAmount;

    private Buyer[] buyers;
    private int buyersAmount;

    public Manager(String systemName) {
        this.sellers = new Seller[2];
        this.buyers = new Buyer[2];
        this.systemName = systemName;
        this.sellersAmount = 0;
        this.buyersAmount = 0;
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

    public void setSystemName(String name) {
        this.systemName = name;
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

    public void addNewBuyer(String username, String password, Address address) {
        Username user = new Username(username, password);

        if (this.buyersAmount == this.buyers.length) {
            increaseBuyersArray();
        }
        this.buyers[this.buyersAmount] = new Buyer(user, address);;
        this.buyersAmount ++;
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
        for (Buyer buyer : this.buyers) {
            if (buyer != null && buyer.getUser().getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void addProductToSeller(int sellerIndex, String productName, double productPrice) {
        Product newProduct = new Product(productName, productPrice);
        this.sellers[sellerIndex].getProductList().addProduct(newProduct);
    }

    public boolean validateSellerIndex(int index) {
        return index >= 0 && index < this.sellersAmount;
    }

    public boolean validateBuyerIndex(int index) {
        return index >= 0 && index < this.buyersAmount;
    }

    public boolean validateProductIndex(int productIndex, int sellerIndex) {
        return productIndex >=0 && productIndex < this.sellers[sellerIndex].getProductList().getProductSize();
    }

    public ProductList getProductsOfSeller(int sellerIndex) {
        return this.sellers[sellerIndex].getProductList();
    }

    public void addProductToCart(int productIndex,int buyerIndex, int sellerIndex) {
        Product requestedProduct = this.sellers[sellerIndex].getProductByIndex(productIndex);
        this.buyers[buyerIndex].getCurrentCart().addProductToCart(new Product(requestedProduct));
    }

    public double getCartPriceByBuyer(int buyerIndex) {
        if (validateBuyerIndex(buyerIndex)) {
            return this.buyers[buyerIndex].getCurrentCart().getPrice();
        }
        return 0;
    }

    private  void increaseSellersArray() {
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
}
