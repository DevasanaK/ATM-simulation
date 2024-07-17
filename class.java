import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;
    private static Scanner scanner = new Scanner(System.in);
    private static ATM account = new ATM("1234", 1000); // Initial balance is 1000 for demonstration

    public ATM(String pin, double initialBalance) {
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawal: " + amount);
            return true;
        }
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: " + amount);
    }

    public void changePin(String newPin) {
        this.pin = newPin;
        transactionHistory.add("PIN changed");
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the ATM Machine");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    balanceInquiry();
                    break;
                case 2:
                    cashWithdrawal();
                    break;
                case 3:
                    cashDeposit();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    transactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM Machine. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void balanceInquiry() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    private static void cashWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Please take your cash.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private static void cashDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Amount deposited successfully.");
    }

    private static void changePin() {
        System.out.print("Enter current PIN: ");
        String currentPin = scanner.next();
        if (account.validatePin(currentPin)) {
            System.out.print("Enter new PIN: ");
            String newPin = scanner.next();
            account.changePin(newPin);
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Invalid current PIN.");
        }
    }

    private static void transactionHistory() {
        ArrayList<String> history = account.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : history) {
                System.out.println(transaction);
            }
        }
    }
}
