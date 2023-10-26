package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Ledger {
    public static void showLedgerOptions(Scanner scanner, List<Transaction> myLists) {
        System.out.print("""
                             <<< LEDGER SCREEN >>>
                             
                Please choose an option to proceed:
                'A' All entries
                'D' Deposits
                'P' Payments
                'R' Reports
                'H' Home
                """);

        char userInput = scanner.next().toUpperCase().charAt(0);

        myLists.sort(new sortMyLists());
        switch (userInput) {
            case 'A':
                OutputSection.displayAllTransactions(myLists);
                break;
            case 'D':
                OutputSection.displayAllDeposits(myLists);
                break;
            case 'P':
                OutputSection.displayAllPayment(myLists);
                break;
            case 'R':
                ReportPart.showReportOptions(scanner, myLists);
                break;
            case 'H':
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }

    }
}
