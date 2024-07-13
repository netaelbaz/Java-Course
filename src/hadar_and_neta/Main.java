//Neta Elbaz (209757020) && Hadar Kaner (207895418)
// we both study with Keren

package hadar_and_neta;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import hadar_and_neta.Product.Category;

public class Main {
    final static String[] menuUsage = {"exit", "add seller", "add buyer", "add product to seller", "add product to buyer",
            "pay for buyer", "print buyer info", "print seller info", "print products of category", "add old order to cart"};
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
            choice = getIntInput("menu choice should be an integer");
            switch (choice) {
                case 0:
                    System.out.println("You requested to exit.");
                    scanner.close();
                    break; // exit
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
                case 9:
                    addOldOrderToCart(manager);
                    break;
                default:
                    System.out.println("Invalid choice");
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
        try {
            User user = new User(username, password);
            manager.addNewSeller(user);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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
        int buildingNumber = getIntInput("Building number should be an integer");
        try {
            Address buyerAddress = new Address(streetName, city, state, buildingNumber);
            User user = new User(username, password);
            manager.addNewBuyer(user, buyerAddress);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printSellerNames(Manager manager) {
        Seller[] sellers = manager.getSellers();
        for (int i = 0; i < manager.getSellersAmount(); i++) {
            System.out.println(i+1 + ". " + sellers[i].getUser().getName());
        }
    }

    private static void printBuyersNames(Manager manager) {
        Buyer[] buyers = manager.getBuyers();
        for (int i = 0; i < manager.getBuyersAmount(); i++) {
            System.out.println(i+1 + ". " + buyers[i].getUser().getName());
        }
    }

    private static void addProductToSeller(Manager manager) {
        if (manager.getSellersAmount() == 0) {
            System.out.println("No sellers yet");
        }
        else {
            System.out.println("Please choose the index of the seller to add the product to: ");
            printSellerNames(manager);
            int sellerIndex = getIntIndex("Seller index should be an integer", manager.getSellersAmount());

            System.out.println("Please enter the name of the product (no spaces): ");
            String productName = scanner.next();

            System.out.println("Please enter the price of the product: ");
            double price = getDoubleInput("Product price should be a double");

            System.out.println("Please enter the index of the product category from the following options:");
            printCategories();
            Category category = getCategory();

            System.out.println("Does your product require special packaging? [y/n]");
            boolean answer = getYesOrNoAnswer();

            Product newProduct;
            try {
                if (answer) {
                    System.out.println("Please enter the price for the packaging");
                    double packagingPrice = getDoubleInput("Packaging price should be a double");
                    newProduct = new SpecialProduct(productName, price , category, packagingPrice);
                }
                else {
                    newProduct = new Product(productName, price , category);
                }
                manager.getSellers()[sellerIndex].getProductList().addProduct(newProduct);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addProductToBuyer(Manager manager) {
        if (manager.getBuyersAmount() == 0 || manager.getSellersAmount() == 0) {
            System.out.println("No buyers or sellers yet, can't finish action");
            return;
        }
            System.out.println("Please choose the index of the buyer to add the product to: ");
            printBuyersNames(manager);
            int buyerIndex = getIntIndex("Buyer index should be an integer", manager.getBuyersAmount());
            System.out.println("Please choose the index of the seller you want to buy from: ");
            printSellerNames(manager);
            int sellerIndex = getIntIndex("Seller index should be an integer", manager.getSellersAmount());

            ProductList sellerProductsList = manager.getProductsOfSeller(sellerIndex);
            int sellerProductsAmount = sellerProductsList.getProductsAmount();
            if (sellerProductsAmount == 0) {
                System.out.println("Seller doesn't have any products. Product not added");
                return;
            }

            System.out.println("Please enter the index of the product from the seller's products: ");
            for (int i = 0; i < sellerProductsAmount; i++) {
                System.out.println((i+1) + ". " +sellerProductsList.getAllProducts()[i].toString());
            }
            int productIndex = getIntIndex("Product index should be an integer", sellerProductsAmount);
            manager.addProductToCart(productIndex, buyerIndex, sellerIndex);
    }

    private static void pay(Manager manager) {
        if (manager.getBuyersAmount() == 0) {
            System.out.println("No buyers yet");
            return;
        }
        System.out.println("Please choose the index of the buyer to pay for: ");
        printBuyersNames(manager);
        int buyerIndex = getIntIndex("Buyer index should be an integer", manager.getBuyersAmount());

        double price = manager.getBuyers()[buyerIndex].getCurrentCart().getPrice();
        System.out.println("The price cart is: " + price);
        if (price == 0) {
            System.out.println("Shopping cart price is 0, stopping payment.");
            return;
        }
        System.out.println("Do you want to pay? [y/n]");
        if (getYesOrNoAnswer()) {
            manager.getBuyers()[buyerIndex].pay();
            System.out.println("Payment succeeded");
        }
    }

    private static void printAllProductsFromCategory(Manager manager) {
        int sellersAmount = manager.getSellersAmount();
        Seller[] sellers = manager.getSellers();
        if (sellersAmount == 0) {
            System.out.println("System doesn't have sellers yet");
            return;
        }
        System.out.println("Please enter the product category from the following options:");
        printCategories();
        Category category = getCategory();
        for (int i = 0; i < sellersAmount; i++) {
            String name = sellers[i].getUser().getName();
            String productsByCategory = sellers[i].getProductList().getProductsByCategory(category);
            if (productsByCategory.isEmpty()){
                System.out.println("There are no products in this category yet");
            }
            else {
                System.out.println( name + ": "+ "\n" + productsByCategory);
            }
        }
    }

    private static void printSellerInfo(Manager manager) {
        if (manager.getSellersAmount() == 0) {
            System.out.println("System doesn't have sellers yet");
        }
        Seller[] copyArray = Arrays.copyOf(manager.getSellers(), manager.getSellersAmount());
        Arrays.sort(copyArray);
        for (int i = 0; i < copyArray.length; i++) {
            System.out.println( i+1 + ") "+ copyArray[i].toString() + '\n');
        }
    }

    private static void printBuyerInfo(Manager manager) {
        if (manager.getBuyersAmount() == 0) {
            System.out.println("System doesn't have buyers yet");
        }
        Buyer[] copyArray = Arrays.copyOf(manager.getBuyers(), manager.getBuyersAmount());
        Arrays.sort(copyArray);
        for (int i = 0; i < copyArray.length; i++) {
            System.out.println( i+1 + ") " + copyArray[i].toString() + '\n');
        }
    }

    private static void printCategories() {
        Category[] allCategories = Category.values();
        for (int i = 0; i < allCategories.length; i++) {
            System.out.println(i+1 + ". "+ allCategories[i].name());
        }
    }

    private static void addOldOrderToCart(Manager manager) {
        if (manager.getBuyersAmount() == 0) {
            System.out.println("System doesn't have buyers yet");
            return;
        }
        System.out.println("Please choose the index of the buyer: ");
        printBuyersNames(manager);
        int buyerIndex = getIntIndex("Buyer index should be an integer", manager.getBuyersAmount());

        Buyer chosenBuyer = manager.getBuyers()[buyerIndex];
        if (chosenBuyer.getCurrentCart().getProductList().getProductsAmount() != 0) {
            System.out.println("Buyer already has products in his current cart, switch with an old cart? [y/n]");
            if (!getYesOrNoAnswer()) {
                return;
            }
        }
        int buyerHistorySize = chosenBuyer.getOrderHistorySize();
        if (buyerHistorySize == 0) {
            System.out.println("Buyer doesnt have order history");
            return;
        }
        System.out.println("Choose the index of the cart you want");
        for (int i=0; i < buyerHistorySize; i ++ ) {
            System.out.println( i+1 + ") " + chosenBuyer.getOrderHistory()[i].toString() + '\n');
        }

        int orderIndex = getIntIndex("Cart index should be an integer", buyerHistorySize);
        try {
            manager.switchToOldCart(chosenBuyer, orderIndex);
        }
        catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported");
        }
    }

    private static int getIntInput(String errorMessage) {
        boolean valid = false;
        int input = 0;
        while (!valid) {
            try {
                input = scanner.nextInt();
                valid = true;
            }
            catch (InputMismatchException e) {
                scanner.next(); // clear buffer
                System.out.println(errorMessage + "\nPlease try again.");
            }
        }
        return input;
    }

    private static double getDoubleInput(String errorMessage) {
        boolean valid = false;
        double input = 0.0;
        while (!valid) {
            try {
                input = scanner.nextDouble();
                valid = true;
            }
            catch (InputMismatchException e) {
                scanner.next(); // clear buffer
                System.out.println(errorMessage + "\nPlease try again.");
            }
        }
        return input;
    }

    private static boolean getYesOrNoAnswer() {
        boolean valid = false;
        String input = null;
        while (!valid) {
            input = scanner.next().toLowerCase();
            valid = (input.equals("y") || input.equals("n"));
            if (!valid) {
                System.out.println("Answer should be only y/n.\nPlease try again");
            }
        }
        return input.equals("y");
    }

    private static int getIntIndex(String errorMessage, int range) {
        boolean valid = false;
        int index = 0;
        while (!valid) {
            index = getIntInput(errorMessage) -1; // display starts from 1 instead of 0
            valid = (index >=0 && index < range);
            if (!valid) {
                System.out.println("Index not in range.\nPlease try again");
            }
        }
        return index;
    }

    private static Category getCategory() {
        boolean invalid = true;
        Category category = null;
        while (invalid) {
            int categoryOption = getIntInput("Category index should be an integer") -1 ;
            try {
                category = Category.values()[categoryOption];
                invalid = false;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Category index not in range.\nPlease try again");
            }
        }
        return category;
    }


    public static void main(String[] args) {
        Manager manager = new Manager("ebay");
        menu(manager);
    }

}