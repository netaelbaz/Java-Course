package hadar_and_neta;

public class ProductList {
    private Product[] allProducts;
    private int productSize;

    public ProductList() {
        this.allProducts = new Product[2];
        this.productSize = 0;
    }

    public Product[] getAllProducts() {
        return this.allProducts;
    }
    public int getProductSize() {
        return this.productSize;
    }

    public void addProduct(Product product) {
        if (this.productSize == this.allProducts.length) {
            increaseProductArray();
        }
        this.allProducts[this.productSize] = product;
        this.productSize ++;
    }
    public String getProductByCategory(Product.Category category) {
        StringBuilder prouductList = new StringBuilder();
        for (int i = 0; i < this.productSize; i++) {
            if (category == this.allProducts[i].getCategory()) {
                prouductList.append( this.allProducts[i].toString() + "\n" );
            }
        }
        return prouductList.toString();
    }

    @Override
    public String toString() {
        StringBuilder productsStr = new StringBuilder();
        if  ( this.productSize == 0 ) {
            productsStr.append("No products yet");
        }
        for (int i = 0; i < this.productSize; i++) {
            productsStr.append( "\n" + (i+1) + ". " + this.allProducts[i].toString());
        }
        return productsStr.toString();
    }

    private void increaseProductArray() {
        Product[] tempArray = new Product[this.allProducts.length *2];
        for (int i = 0; i < this.productSize; i++) {
            tempArray[i] = this.allProducts[i];
        }
        this.allProducts = tempArray;
    }

}
