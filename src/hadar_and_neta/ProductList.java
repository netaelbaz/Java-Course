package hadar_and_neta;

import java.util.Arrays;

public class ProductList implements Cloneable{
    private Product[] allProducts;
    private int productsAmount;

    public ProductList() {
        this.allProducts = new Product[2];
        this.productsAmount = 0;
    }

    public Product[] getAllProducts() {
        return this.allProducts;
    }
    public int getProductsAmount() {
        return this.productsAmount;
    }

    public void addProduct(Product product) {
        if (this.productsAmount == this.allProducts.length) {
            increaseProductArray();
        }
        this.allProducts[this.productsAmount] = product;
        this.productsAmount++;
    }

    public String getProductsByCategory(Product.Category category) {
        StringBuilder productList = new StringBuilder();
        for (int i = 0; i < this.productsAmount; i++) {
            if (category == this.allProducts[i].getCategory()) {
                productList.append(this.allProducts[i].toString()).append("\n");
            }
        }
        return productList.toString();
    }

    @Override
    public String toString() {
        StringBuilder productsStr = new StringBuilder();
        if  ( this.productsAmount == 0 ) {
            productsStr.append("No products yet");
        }
        for (int i = 0; i < this.productsAmount; i++) {
            productsStr.append("\n").append(i + 1).append(". ").append(this.allProducts[i].toString());
        }
        return productsStr.toString();
    }

    @Override
    public ProductList clone() throws CloneNotSupportedException {
        ProductList temp = (ProductList)super.clone();
        Product[] duplicateProducts = new Product[this.allProducts.length];
        for (int i = 0; i< this.productsAmount; i++) {
            duplicateProducts[i] = allProducts[i].clone();
        }
        temp.allProducts = duplicateProducts;
        return temp;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ProductList)) {
            return false;
        }
        ProductList otherProductList = (ProductList)other;
        return otherProductList.productsAmount == this.productsAmount &&
                Arrays.equals(this.allProducts, otherProductList.allProducts);
    }

    private void increaseProductArray() {
        Product[] tempArray = new Product[this.allProducts.length *2];
        for (int i = 0; i < this.productsAmount; i++) {
            tempArray[i] = this.allProducts[i];
        }
        this.allProducts = tempArray;
    }

}
