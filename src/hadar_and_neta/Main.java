//Neta Elbaz (209757020) && Hadar Kaner (207895418)
// we both study with Keren

package hadar_and_neta;

import java.util.Scanner;
import hadar_and_neta.Product.Category;

public class Main {
    final static String[] menuUsage = {"exit", "add seller", "add buyer", "add product to seller", "add product to buyer",
            "pay for buyer", "print buyer info", "print seller info", "print products of category" };
    static Scanner scanner = new Scanner(System.in);

    private static void printMenuUsage() {
        System.out.println("Please enter a number from the following options");
        for (int i = 0; i < menuUsage.length; i++) {
            System.out.println(i + ") " + menuUsage[i]);
        }
    }

    private static void menu(Manager manager) {
        int choice;
        do {
            printMenuUsage();
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("You requested to exit.");
                    scanner.close();
                    break;
                case 1:
                    addNewSeller(manager);
                    break;
                case 2:
                    addNewBuyer(manager);
                    break;
                case 3:
                    addProductToSeller(manager);
                    break;
                case 4:
                    addProductToBuyer(manager);
                    break;
                case 5:
                    pay(manager);
                    break;
                case 6:
                    printBuyerInfo(manager);
                    break;
                case 7:
                    printSellerInfo(manager);
                    break;
                case 8:
                    printAllProductsFromCategory(manager);
                    break;
                default:
                    System.out.println("invalid choice");

            }
        }
        while (choice != 0);
    }
    private static void addNewSeller(Manager manager) {
        String username;
        boolean isDuplicate;
        do {
            System.out.println("Please enter the seller username (no spaces)");
            username = scanner.next();
            isDuplicate = manager.findDuplicateSellerName(username);
            if (isDuplicate) {
                System.out.println("Username taken");
            }
        } while(isDuplicate);
        System.out.println("Please enter the seller password (no spaces)");
        String password = scanner.next();
        manager.addNewSeller(username, password);

    }

    private static void addNewBuyer(Manager manager) {
        String username;
        boolean isDuplicate;
        do {
            System.out.println("Please enter the buyer username (no spaces)");
            username = scanner.next();
            isDuplicate = manager.findDuplicateBuyerName(username);
            if (isDuplicate) {
                System.out.println("Username taken");
            }
        } while(isDuplicate);
        System.out.println("Please enter the buyer's password (no spaces)");
        String password = scanner.next();
        System.out.println("Please enter the buyer's state (no spaces)");
        String state = scanner.next();
        scanner.nextLine(); // consume left over characters
        System.out.println("Please enter the buyer's city");
        String city = scanner.nextLine();
        System.out.println("Please enter the buyer's street name");
        String streetName = scanner.nextLine();
        System.out.println("Please enter the buyer's building number");
        int buildingNumber = scanner.nextInt();
        Address buyerAddress = new Address(streetName, city, state, buildingNumber);
        manager.addNewBuyer(username, password, buyerAddress);
    }

    private static String displaySellerNames(Manager manager) {
        StringBuilder sellerNames = new StringBuilder();
        Seller[] sellers = manager.getSellers();
        for (int i = 0; i < manager.getSellersAmount(); i++) {
            sellerNames.append( i+1 + ". " + sellers[i].getUser().getName() + "\n");
        }
        return sellerNames.toString();
    }

    private static String displayBuyerNames(Manager manager) {
        StringBuilder buyerNames = new StringBuilder();
        Buyer[] buyers = manager.getBuyers();
        for (int i = 0; i < manager.getBuyersAmount(); i++) {
            buyerNames.append( i+1 + ". " + buyers[i].getUser().getName() + "\n");
        }
        return buyerNames.toString();
    }

    private static void addProductToSeller(Manager manager) {
        if (manager.getSellersAmount() == 0) {
            System.out.println("No sellers yet");
        }
        else {
            System.out.println("Please choose the number of the seller to add the product to: ");
            System.out.println(displaySellerNames(manager));
            int sellerIndex = scanner.nextInt() -1;
            if (!manager.validateSellerIndex(sellerIndex)) {
                System.out.println("Seller index does not exists. Product not added");
                return;
            }
            System.out.println("Please enter the name of the product (no spaces): ");
            String productName = scanner.next();
            System.out.println("Please enter the price of the product: ");
            double price = scanner.nextDouble();
            System.out.println("Please enter the product category from the following options:");
            printCategories();
            Product.Category category = Product.Category.valueOf(scanner.next().toUpperCase());
            System.out.println("Does your product require special packaging? [y/n]");
            char answer = scanner.next().toLowerCase().charAt(0);
            Product newProduct;
            if (answer == 'y') {
                System.out.println("Please enter the price for the packaging");
                double packagingPrice = scanner.nextDouble();
                newProduct = new SpecialProduct(productName, price , category, packagingPrice);
            }
            else {
                newProduct = new Product(productName, price , category);
            }
            manager.getSellers()[sellerIndex].getProductList().addProduct(newProduct);
        }
    }

    private static void addProductToBuyer(Manager manager) {
        if (manager.getBuyersAmount() == 0 || manager.getSellersAmount() == 0) {
            System.out.println("No buyers or sellers yet, can't finish action");
            return;
        }
        System.out.println("Please choose the number of the buyer to add the product to: ");
        System.out.println(displayBuyerNames(manager));
        int buyerIndex = scanner.nextInt() -1; // display starts from one and not zero
        if (!manager.validateBuyerIndex(buyerIndex)) {
            System.out.println("Buyer index does not exists. Product not added");
            return;
        }
        System.out.println("Please choose the number of the seller you want to buy from: ");
        System.out.println(displaySellerNames(manager));
        int sellerIndex = scanner.nextInt() -1;
        if (!manager.validateSellerIndex(sellerIndex)) {
            System.out.println("Seller index does not exists. Product not added");
            return;
        }
        ProductList productsOfSeller = manager.getProductsOfSeller(sellerIndex);
        if (productsOfSeller.getProductSize() == 0) {
            System.out.println("Seller doesn't have any products. Product not added");
            return;
        }
        System.out.println("Please enter the number of the product from the seller's products: ");
        for (int i = 0; i < productsOfSeller.getProductSize(); i++) {
            System.out.println((i+1) + ". " +productsOfSeller.getAllProducts()[i].toString());
        }
        int productIndex = scanner.nextInt() -1;
        if (!manager.validateProductIndex(productIndex, sellerIndex)) {
            System.out.println("Product index is invalid. Product not added");
            return;
        }
        manager.addProductToCart(productIndex, buyerIndex, sellerIndex);
    }

    private static void pay(Manager manager) {
        if (manager.getBuyersAmount() == 0) {
            System.out.println("No buyers yet");
            return;
        }
        System.out.println("Please choose the number of the buyer to pay for: ");
        System.out.println(displayBuyerNames(manager));
        int buyerIndex = scanner.nextInt() - 1;
        if (!manager.validateBuyerIndex(buyerIndex)) {
            System.out.println("Buyer index does not exists.");
            return;
        }
        double price = manager.getCartPriceByBuyer(buyerIndex);
        System.out.println("The price cart is: " + price);
        if (price == 0) {
            System.out.println("Stopping payment.");
            return;
        }
        System.out.println("Do you want to pay? [y/n]");
        char answer = scanner.next().toLowerCase().charAt(0);
        if (answer == 'y') {
            manager.getBuyers()[buyerIndex].pay();
            System.out.println("Payment succeeded");
        }
    }

    private static void printAllProductsFromCategory(Manager manager) {
        System.out.println("Please enter the product category from the following options:");
        printCategories();
        Product.Category category = Product.Category.valueOf(scanner.next().toUpperCase());
        System.out.println(manager.getSellers()[0].getProductList().getProductByCategory(category));
    }

    private static void printSellerInfo(Manager manager) {
        if (manager.getSellersAmount() == 0) {
            System.out.println("System doesn't have sellers yet");
        }
        for (int i = 0; i < manager.getSellersAmount(); i++) {
            System.out.println( i+1 + ") "+ manager.getSellers()[i].toString() + '\n');
        }
    }

    private static void printBuyerInfo(Manager manager) {
        if (manager.getBuyersAmount() == 0) {
            System.out.println("System doesn't have buyers yet");
        }
        for (int i = 0; i < manager.getBuyersAmount(); i++) {
            System.out.println( i+1 + ") " + manager.getBuyers()[i].toString() + '\n');
        }
    }

    private static void printCategories() {
        for (Category category : Category.values()) {
            System.out.println(category);
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager("ebay");
        menu(manager);
    }

}