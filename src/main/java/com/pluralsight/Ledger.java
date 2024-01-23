package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Ledger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static void showLedgerOptions(Scanner scanner, List<Transaction> myLists) {
        System.out.print(ANSI_GREEN + """
                             <<< LEDGER SCREEN >>>
                             
                Please choose an option to proceed:
                """ + ANSI_YELLOW + "'A' All entries\n'D' Deposits\n'P' Payments\n'R' Reports\n'H' Home" + ANSI_BLUE + """
                """ + ANSI_RESET);

        char userInput = scanner.next().toUpperCase().charAt(0);

        myLists.sort(new sortMyLists());
        switch (userInput) {
            case 'A':
                OutputSection.displayAllTransactions(myLists);
                askUserToContinueOrExit(scanner);
                break;
            case 'D':
                OutputSection.displayAllDeposits(myLists);
                askUserToContinueOrExit(scanner);
                break;
            case 'P':
                OutputSection.displayAllPayment(myLists);
                askUserToContinueOrExit(scanner);
                break;
            case 'R':
                ReportPart.showReportOptions(scanner, myLists);
                break;
            case 'H':
                break;
            default:
                System.out.println(ANSI_GREEN + "Invalid option. Please try again." + ANSI_RESET);
        }

    }

    public static void askUserToContinueOrExit(Scanner scanner)
    {
        System.out.println("Do you want to continue or exit: ");
        char answer = scanner.next().charAt(0);
        if(answer == 'n'|| answer == 'N')
        {
            System.exit(0);
        }

    }
}
