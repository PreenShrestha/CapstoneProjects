package com.pluralsight;


import java.util.Comparator;
import java.util.Scanner;
import java.util.List;


public class AccountingLedger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //taking user input

        List<Transaction> myLists = OutputSection.getTransaction(); //Load initial data


        while (true) { //display homeScreen
            System.out.print(ANSI_YELLOW + """
                                                
                             ┌───────────────────────────────────────────┐
                             │   Welcome to The Financial Tracker App!   │
                             └───────────────────────────────────────────┘
                             
                                         <<< HOME SCREEN >>>
                                     
                    Please Choose an Option to Start:
                    """ + ANSI_BLUE + "'A' Add Deposit\n'M' Make Payment\n'L' Ledger\n'X' Exit" + ANSI_BLUE + """
                    """ + ANSI_RESET);
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
                    System.out.println(ANSI_GREEN + "Invalid option. Please try again." + ANSI_RESET);
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




