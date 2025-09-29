package com.ElevateLabs;
import java.util.*;

class Account {
   protected String accountNumber;
   protected String accountHolderName;
   protected double balance;
   protected List<String> transactionHistory;

   public Account(String accountNumber, String accountHolderName, double initialBalance) {
      this.accountNumber = accountNumber;
      this.accountHolderName = accountHolderName;
      this.balance = initialBalance;
      this.transactionHistory = new ArrayList<>();
      transactionHistory.add("Account created with balance: " + initialBalance);
   }

   public void deposit(double amount) {
      balance += amount;
      transactionHistory.add("Deposited: " + amount + " | Balance: " + balance);
   }

   public void withdraw(double amount) {
      if (balance >= amount) {
         balance -= amount;
         transactionHistory.add("Withdraw: " + amount + " | Balance: " + balance);
      } else transactionHistory.add("Failed Withdraw: " + amount + " (Insufficient Funds)");
   }

   public void showTransactionHistory() {
      System.out.println("Transaction History for " + accountHolderName + ":");
      for (String record : transactionHistory) System.out.println(record);
   }
}

class SavingsAccount extends Account {
   private static final double MIN_BALANCE = 500.0;

   public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
      super(accountNumber, accountHolderName, initialBalance);
   }

   @Override
   public void withdraw(double amount) {
      if (balance - amount >= MIN_BALANCE) {
         balance -= amount;
         transactionHistory.add("Withdrawn (Savings): " + amount + " | Balance: " + balance);
      } else transactionHistory.add("Failed withdrawal: " + amount + " (Below min balance)");
   }
}

class CurrentAccount extends Account {
   public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance) {
      super(accountNumber, accountHolderName, initialBalance);
   }

   @Override
   public void withdraw(double amount) {
      if (balance >= amount) {
         balance -= amount;
         transactionHistory.add("Withdrawn (Current): " + amount + " | Balance: " + balance);
      } else transactionHistory.add("Failed withdrawal: " + amount + " (Insufficient funds)");
   }
}

public class BankAccountSimulation {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      Account account = null;

      System.out.print("Enter Account Type (1: Savings, 2: Current): ");
      int type = sc.nextInt();
      sc.nextLine();
      System.out.print("Enter Account Number: ");
      String accNum = sc.nextLine();
      System.out.print("Enter Account Holder Name: ");
      String holder = sc.nextLine();
      System.out.print("Enter Initial Balance: ");
      double balance = sc.nextDouble();

      if (type == 1) {
         account = new SavingsAccount(accNum, holder, balance);
      } else if (type == 2) {
         account = new CurrentAccount(accNum, holder, balance);
      } else {
         System.out.println("Invalid account type! Exiting...");
         return;
      }

      int choice;
      do {
         System.out.println("\n--- Bank Menu ---");
         System.out.println("1. Deposit");
         System.out.println("2. Withdraw");
         System.out.println("3. Show Transaction History");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");
         choice = sc.nextInt();

         switch (choice) {
            case 1:
               System.out.print("Enter Deposit Ammount: ");
               double dep = sc.nextDouble();
               account.deposit(dep);
               System.out.println(dep+" Ammount is Successfuly Deposite.");
               break;
            case 2:
               System.out.print("Enter Withdraw Ammount: ");
               double wd = sc.nextDouble();
               account.withdraw(wd);
               System.out.println(wd+" Ammount is Successfuly Withdrawn.");
               break;
            case 3:
               account.showTransactionHistory();
               break;
            case 4:
               System.out.println("Exiting... Thank you!");
               break;
            default:
               System.out.println("Invalid choice, Try Again.");
         }
      } while (choice != 4);
   }
}