package com.pluralsight;


import java.util.Comparator;
import java.util.Scanner;
import java.util.List;


public class AccountingLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //taking user input

        List<Transaction> myLists = OutputSection.getTransaction(); //Load initial data


        while (true) { //display homeScreen
            System.out.print("""
                                                
                             ┌───────────────────────────────────────────┐
                             │   Welcome to The Financial Tracker App!   │
                             └───────────────────────────────────────────┘
                             
                                         <<< HOME SCREEN >>>
                                     
                    Please Choose an Option to Start:
                    'A' Add Deposit
                    'M' Make Payment
                    'L' Ledger
                    'X' Exit
                    """);
            char option = scanner.next().toUpperCase().charAt(0); //make the first letter to uppercase

            switch (option) { //user chooses the options
                case 'A':
                    InputSection.makeDeposit(myLists);
                    break;
                case 'M':
                    InputSection.makePayment(myLists);
                    break;
                case 'L':
                    Ledger.showLedgerOptions(scanner, myLists);
                    break;
                case 'X':
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


}

class sortMyLists implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // Compare transactions based on the date and time
        int dateComparison = t1.getDate().compareTo(t2.getDate());

        // If the dates are equal, compare the time
        if (dateComparison == 0) {
            return t1.getTime().compareTo(t2.getTime());
        }

        return dateComparison;
    }
}




