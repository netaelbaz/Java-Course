package hadar_and_neta;

public class ProductList implements Cloneable{
    private Product[] allProducts;
    private int productAmount;

    public ProductList() {
        this.allProducts = new Product[2];
        this.productAmount = 0;
    }

    public Product[] getAllProducts() {
        return this.allProducts;
    }
    public int getProductAmount() {
        return this.productAmount;
    }

    public void addProduct(Product product) {
        if (this.productAmount == this.allProducts.length) {
            increaseProductArray();
        }
        this.allProducts[this.productAmount] = product;
        this.productAmount++;
    }
    public String getProductByCategory(Product.Category category) {
        StringBuilder productList = new StringBuilder();
        for (int i = 0; i < this.productAmount; i++) {
            if (category == this.allProducts[i].getCategory()) {
                productList.append( this.allProducts[i].toString() + "\n" );
            }
        }
        return productList.toString();
    }

    @Override
    public String toString() {
        StringBuilder productsStr = new StringBuilder();
        if  ( this.productAmount == 0 ) {
            productsStr.append("No products yet");
        }
        for (int i = 0; i < this.productAmount; i++) {
            productsStr.append( "\n" + (i+1) + ". " + this.allProducts[i].toString());
        }
        return productsStr.toString();
    }

    @Override
    public ProductList clone() throws CloneNotSupportedException {
        ProductList temp = (ProductList)super.clone();
        Product[] duplicateProducts = new Product[this.allProducts.length];
        for (int i=0; i< this.productAmount; i++) {
            duplicateProducts[i] = allProducts[i].clone();
        }
        temp.allProducts = duplicateProducts;
        return temp;
    }

    private void increaseProductArray() {
        Product[] tempArray = new Product[this.allProducts.length *2];
        for (int i = 0; i < this.productAmount; i++) {
            tempArray[i] = this.allProducts[i];
        }
        this.allProducts = tempArray;
    }

}
