//Neta Elbaz (209757020) && Hadar Kaner (207895418)
// we both study with Keren

import hadar_and_neta.Address;
import hadar_and_neta.Manager;

import java.util.Scanner;

public class Main {
    final static String[] menuUsage = {"exit", "add seller", "add buyer", "add product to seller", "add product to buyer",
            "pay for buyer", "print buyer info", "print seller info"};
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
            System.out.println("Please enter the seller username");
            username = scanner.next();
            isDuplicate = manager.findDuplicateSellerName(username);
            if (isDuplicate) {
                System.out.println("Username taken");
            }
        } while(isDuplicate);
        System.out.println("Please enter the seller password");
        String password = scanner.next();
        manager.addNewSeller(username, password);

    }

    private static void addNewBuyer(Manager manager) {
        String username;
        boolean isDuplicate;
        do {
            System.out.println("Please enter the buyer username");
            username = scanner.next();
            isDuplicate = manager.findDuplicateBuyerName(username);
            if (isDuplicate) {
                System.out.println("Username taken");
            }
        } while(isDuplicate);
        System.out.println("Please enter the buyer's password");
        String password = scanner.next();
        System.out.println("Please enter the buyer's street name");
        String streetName = scanner.next();
        System.out.println("Please enter the buyer's building number");
        int buildingNumber = scanner.nextInt();
        System.out.println("Please enter the buyer's city");
        String city = scanner.next();
        System.out.println("Please enter the buyer's state");
        String state = scanner.next();
        Address buyerAddress = manager.createAddress(streetName, city, state, buildingNumber);
        manager.addNewBuyer(username, password, buyerAddress);
    }

    private static void addProductToSeller(Manager manager) {
        if (manager.getSellersAmount() == 0) {
            System.out.println("No sellers yet");
        }
        else {
            System.out.println("Please choose the number of the seller to add the product to: ");
            System.out.println(manager.getSellersName());
            int sellerIndex = scanner.nextInt();
            System.out.println("Please enter the name of the product: ");
            String productName = scanner.next();
            System.out.println("Please enter the price of the product: ");
            double price = scanner.nextDouble();
            if (!manager.addProductToSeller(sellerIndex-1, productName, price)) {
                System.out.println("Action failed. Seller index do not exist");
            }
        }
    }

    private static void addProductToBuyer(Manager manager) {
        if (manager.getBuyersAmount() == 0 || manager.getSellersAmount() == 0) { // move checks to manager
            System.out.println("No buyers or sellers yet, can't finish action");
            return;
        }
        System.out.println("Please choose the number of the buyer to add the product to: ");
        System.out.println(manager.getBuyersName());
        int buyerIndex = scanner.nextInt();
        System.out.println("Please choose the number of the seller you want to buy from: ");
        System.out.println(manager.getSellersName());
        int sellerIndex = scanner.nextInt();
        String productsOfSeller = manager.getProductsOfSeller(sellerIndex-1);
        if (productsOfSeller == null) {
            System.out.println("Action failed, seller does not exits or doesn't have any products. product not added");
            return;
        }
        System.out.println("Please enter the number of the product from the seller's products: ");
        System.out.println(productsOfSeller);
        int productIndex = scanner.nextInt();
        if (!manager.addProductToCart(productIndex-1, buyerIndex-1, sellerIndex-1)) {
            System.out.println("Action failed, buyer or product does not exits. product not added");
        };
    }

    private static void pay(Manager manager) {
        if (manager.getBuyersAmount() == 0) {
            System.out.println("No buyers yet");
            return;
        }
        System.out.println("Please choose the number of the buyer to pay for: ");
        System.out.println(manager.getBuyersName());
        int buyerIndex = scanner.nextInt();
        if (!manager.validateBuyerIndex(buyerIndex-1)) {
            System.out.println("Buyer index does not exists.");
            return;
        }
        double price = manager.getCartPriceByBuyer(buyerIndex-1);
        System.out.println("The price cart is: " + price);
        if (price == 0) {
            System.out.println("Stopping payment.");
            return;
        }
        System.out.println("Do you want to pay? [y/n]");
        char answer = scanner.next().toLowerCase().charAt(0);
        if (answer == 'y') {
            manager.getBuyers()[buyerIndex-1].pay();
        }
    }

    private static void printSellerInfo(Manager manager) {
        for (int i = 0; i < manager.getSellersAmount(); i++) {
            System.out.println(manager.getSellers()[i].toString());
        }
    }

    private static void printBuyerInfo(Manager manager) {
        for (int i = 0; i < manager.getBuyersAmount(); i++) {
            System.out.println(manager.getBuyers()[i].toString());
        }
    }

    public static void main(String[] args) {
        String n1 = "neta";
        String n2 = "hadar";
        String n3 = "niv";
        String n4 = "itamar";
        String p1 = "djfj";
        String p2 = "djfj";
        Address add = new Address("bla", "fsf", "israel", 4);
        Manager manager = new Manager("ebay");
        manager.addNewSeller(n1,p1);
        manager.addNewSeller(n3,p1);
        manager.addNewBuyer(n2, p2, add);
        manager.addNewBuyer(n4, p2, add);
        menu(manager);
    }

}