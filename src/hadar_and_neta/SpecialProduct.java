package hadar_and_neta;

public class SpecialProduct extends Product {
    private double packagingPrice;

    public SpecialProduct(String name, double price, Category category, double packagingPrice) {
        super(name, price, category);
        this.packagingPrice = packagingPrice;
    }

    public double getPackagingPrice() {
        return this.packagingPrice;
    }

    public void setPackagingPrice(double packagingPrice) {
        this.packagingPrice = packagingPrice;
    }

    @Override
    public String toString() {
        return super.toString() + ", Packaging price = " + this.packagingPrice
                + ", Total price = " + ( this.getPrice() + this.packagingPrice ) ;
    }
}
