package com.pluralsight;

import java.util.List;

public class OutputSection {
    public static void displayAllTransactions(List<Transaction> lists) {
        System.out.print("""
                                   <<< All Transactions >>>
                                       
                """);

        for (int i=lists.size() -1; i >= 0; i--) {
            Transaction transaction = lists.get(i);
            System.out.printf("%-15s %-10s %-35s %-20s $%.02f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
        //Transaction t[i]
        //t[i].getDate(), getTime(),......
    }

    public static void displayAllDeposits(List<Transaction> lists) {
        System.out.print(""" 
                              <<< All Deposit Transactions >>>
                             
             """);
        System.out.printf("%-15s %-10s %-35s %-20s %9s%n", "Date", "Time", "Description", "Vendor", "Amount");

        for (int i = lists.size()-1; i>=0; i--) {
            Transaction transaction = lists.get(i);
            if (transaction.getAmount() < 0) { // Check if the amount is negative (indicating a deposit)
                System.out.printf("%-15s %-10s %-35s %-20s $%.2f%n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
    }


    public static void displayAllPayment(List<Transaction> lists) //create function for displaying all
    {
        System.out.print("""
                                <<< All Payments Transactions >>>
                                    
             """);
        System.out.printf("%-15s %-10s %-35s %-20s %-10s%n", "Date", "Time", "Description", "Receiver", "Amount");

        for(int i= lists.size()-1; i>=0; i--) {
            Transaction transaction = lists.get(i);
            if(transaction.getAmount() <0) {
                String s = String.format("%-16s %-10s %-35s %-30s $%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), -transaction.getAmount());
            }
        }


    }
}
