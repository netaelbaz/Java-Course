package hadar_and_neta;

public class SpecialProduct extends Product {
    private double packagingPrice;

    public SpecialProduct(String name, double price, Category category, double packagingPrice)
            throws IllegalArgumentException{
        super(name, price, category);
        setPackagingPrice(packagingPrice);
    }

    public double getPackagingPrice() {
        return this.packagingPrice;
    }

    public void setPackagingPrice(double packagingPrice) throws IllegalArgumentException {
        if (packagingPrice == 0) {
            throw new IllegalArgumentException("Packaging price can't be zero");
        }
        this.packagingPrice = packagingPrice;
    }

    @Override
    public String toString() {
        return super.toString() + ", Packaging Price = " + this.packagingPrice
                + ", Total Price = " + ( this.getPrice() + this.packagingPrice ) ;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SpecialProduct)) {
            return false;
        }
        if (!(super.equals(other))) {
            return false;
        }
        SpecialProduct otherSpecialProduct = (SpecialProduct)other;
        return otherSpecialProduct.packagingPrice == this.packagingPrice;
    }
}
