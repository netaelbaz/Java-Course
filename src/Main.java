//Neta Elbaz (209757020) && Hadar Kaner (207895418)
// we both study with Keren

import java.util.Scanner;

public class Main {
    final static String[] menuUsage = {"exit", "add seller", "add buyer", "add product to seller", "add product to buyer",
            "pay for buyer", "print buyer info", "print seller info"};
    static Scanner scanner = new Scanner(System.in);
    static Manager manager = new Manager();


    private static void printMenuUsage() {
        System.out.println("Please enter a number from the following options");
        for (int i = 0; i < menuUsage.length; i++) {
            System.out.println(i + ") " + menuUsage[i]);
        }
    }

    private static void menu(){
        int choice;
        do {
            printMenuUsage();
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("You requested to exit.");
                    break;
                case 1:
                    addNewSeller();
                    break;
                case 2:
                    addNewBuyer();
                    break;
                case 3:
//                    addProductSeller();
                    break;
                case 4:
//                    addProductBuyer();
                    break;
                case 5:
//                    payment();
                    break;
                case 6:
//                    printInfo(buyers, buyersAmount);
                    break;
                case 7:
//                    printInfo(sellers, sellersAmount);
                    break;
                default:
                    System.out.println("invalid choice");

            }
        }
        while (choice != 0);
    }

    private static void addNewSeller() {
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

    private static void addNewBuyer() {
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

//    private static String getUsername(String[] array, String type) {
//        // request username from user until no duplicate username is entered
//        String username;
//        boolean isDuplicate;
//        do {
//            System.out.printf("Please enter your username for %s\n", type);
//            username = scanner.next();
//            isDuplicate = findDuplicateName(array, username);
//            if (isDuplicate) {
//                System.out.println("Username taken");
//            }
//        } while(isDuplicate);
//        return username;
//    }
//
//    private static void addUsernameToSellers(){
//        // add username to sellers array
//        String username = getUsername(sellers, "seller");
//
//        if (sellersAmount == sellers.length) {
//            sellers = increaseArray(sellers, sellersAmount);
//        }
//        sellers[sellersAmount] = username;
//        sellersAmount += 1;
//    }
//
//    private static void addUsernameToBuyers(){
//        // add username to buyers array
//        String username = getUsername(buyers, "buyer");
//
//        if (buyersAmount == buyers.length) {
//            buyers = increaseArray(buyers, buyersAmount);
//        }
//        buyers[buyersAmount] = username;
//        buyersAmount += 1;
//
//    }

//    private static void addProductSeller() {
//        // get product info from user and add the product to selected seller
//        if (sellersAmount == 0) {
//            System.out.println("No sellers yet, can't finish action");
//            return;
//        }
//        System.out.println("Please choose the number of the seller to add the product to: ");
//        printInfo(sellers, sellersAmount);
//        int sellerIndex = scanner.nextInt();
//        System.out.println("Please enter the name of the product: ");
//        String productName = scanner.next();
//        System.out.println("Please enter the price of the product: ");
//        double price = scanner.nextDouble();
//        System.out.println("Please enter the category: ");
//        String category = scanner.next();
//    }
//    private static void addProductBuyer() {
//        // add product to buyer and specify which seller to buy from
//        if (buyersAmount == 0 | sellersAmount == 0) {
//            System.out.println("No buyers or sellers yet, can't finish action");
//            return;
//        }
//        System.out.println("Please choose the number of the buyer to add the product to: ");
//        printInfo(buyers, buyersAmount);
//        int buyerIndex = scanner.nextInt();
//        System.out.println("Please choose the number of the seller you want to buy from: ");
//        printInfo(sellers, sellersAmount);
//        int sellerIndex = scanner.nextInt();
//    }
//    private static void payment() {
//        // get buyer name to pay for
//        if (buyersAmount == 0) {
//            System.out.println("No buyers yet, can't finish action");
//            return;
//        }
//        System.out.println("Please choose the number of the buyer you want to pay for: ");
//        printInfo(buyers, buyersAmount);
//        int buyerIndex = scanner.nextInt();
//    }
//    private static void printInfo(String[] array ,int arrayAmount){
//        if (arrayAmount == 0) {
//            System.out.println("No items yet");
//        }
//        for (int i = 0; i < arrayAmount; i++) {
//            System.out.println(i+1 + ". " + array[i]);
//        }
//}
    public static void main(String[] args) {
        menu();
    }

}