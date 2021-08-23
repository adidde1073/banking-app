import models.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class App {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    public static Account currentAccount = null;
    public static Customer currentUser;
    public static Random newAccount = new Random();
    public static Long newAccountNo;

    public static void main(String[] args) {
        /* default data */
        Customer Chad = new Customer("chad", "pass", 1234);
        customers.add(Chad);
        Account chadCount = new Account(1234, 500);
        accounts.add(chadCount);
        /* Refactor application to use proper variables */
        Scanner in = new Scanner(System.in);
        String userChoice;
        boolean quit = false;
        float balance = 0;


        /* TODO */
        boolean loggedIn = false;

        do {
            if(loggedIn) {
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Check balance");
            System.out.print("Press 0 to quit: ");

             userChoice = in.next();


                switch (userChoice) {
                    case "1":
                        float amount;
                        System.out.print("Amount to deposit: ");
                        amount = in.nextFloat();
                        if (amount <= 0)
                            System.out.println("Can't deposit nonpositive amount.");
                        else {
                            currentAccount.makeDeposit(amount);
                            System.out.println("$" + amount + " has been deposited.");
                        }
                        break;
                    case "2":
                        System.out.print("Amount to withdraw: ");
                        amount = in.nextFloat();
                        if (amount <= 0 || amount > currentAccount.getBalance())
                            System.out.println("Withdrawal could not be completed: insufficient funds");
                        else {
                            currentAccount.makeWithdrawal(amount);
                            System.out.println("$" + amount + " has been withdrawn.");
                        }
                        break;
                    case "3":
                        System.out.println("Your balance: $" + currentAccount.getBalance());
                        break;
                    case "0":
                        quit = true;
                        break;
                    default:
                        System.out.println("Wrong choice.");
                        break;
                }
                System.out.println();
            } else {
                // TODO
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.print("Press 0 to quit: ");

                userChoice = in.next();

                switch (userChoice) {
                    case "1":
                        String username;
                        String password;
                        System.out.println("Enter your username: ");
                        username = in.next();
                        if(dbContainsUser(username)) {
                            System.out.println("Enter your password: ");
                            password = in.next();
                            if(password.equals(currentUser.getPassword())) {
                                System.out.println("Welcome, " + currentUser.getUsername());
                                loggedIn=true;
                            } else {
                                System.out.println("Invalid password. Please try again");
                            }
                        } else {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                        break;
                    case "2": // TODO register
                        Float newBalance;
                        System.out.print("Please enter a username: ");
                        username = in.next();
                        if(!dbContainsUser(username)) {
                            System.out.println("Please enter a password: ");
                            password = in.next();
                            System.out.println("Please enter a starting balance: ");
                            newBalance = in.nextFloat();
                            registerNewCustomer(username, password, newBalance);
                            System.out.println("Registration complete.");
                            loggedIn=true;
                        } else {
                            System.out.println("Username already exists. Please try again using another username");
                        }
                        break;
                    case "0":
                        quit = true;
                        break;
                    default:
                        System.out.println("Wrong choice.");
                        break;
                }
            }
        } while (!quit);

        System.out.println("Bye!");
    }

    // Customer methods
    public static void registerNewCustomer(String username, String password, float balance) {
        newAccountNo = Long.valueOf(newAccount.nextInt());
        currentUser = new Customer(username, password, newAccountNo);
        currentAccount = new Account(newAccountNo, balance);
        customers.add(currentUser);
        accounts.add(currentAccount);

    }

    // "DAO" methods
    public static boolean dbContainsUser(String username) {
        for(Customer c : customers) {
            if(c.getUsername().equals(username)) {
                currentUser = c;
                for(Account a : accounts) {
                    if(a.getAccountNo() == c.getAccountNo()) {
                        currentAccount = a;
                    }
                }
                System.out.println(c.getUsername());
                return true;
            }
        }
        return false;
    }
}
