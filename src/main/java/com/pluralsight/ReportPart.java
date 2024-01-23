package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ReportPart {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void showReportOptions(Scanner scanner, List<Transaction> myLists) {
        System.out.print(ANSI_BLUE + """
                             <<< REPORTS SCREEN >>>
                             
                Please choose an option to proceed:
                """ + ANSI_YELLOW + "1) Month to Date\n2) Previous Month\n3) Year To Date\n4) Previous Year\n" +
                "5) Search by Vendor\n6) Custom Search\n7) Back" + ANSI_BLUE + """
                """ + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\nPlease choose your option: " + ANSI_RESET);
        int usersInput = scanner.nextInt();

        LocalDateTime currentTime = LocalDateTime.now(); //This line retrieves the current date and time
        // using the LocalDateTime class. It captures the current moment, including both the date and time.
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //specify the desired format for the date
        String currentDate = currentTime.format(formatting); //This line formats the current date and time, represented by the currentTime variable

        switch (usersInput) {
            case 1:
                OutputSection.displayMonthToDate();
                break;
            case 2:
                OutputSection.displayPreviousMonth();
                break;
            case 3:
                OutputSection.displayYearToDate();
                break;
            case 4:
                OutputSection.displayPreviousYear();
                break;
            case 5:
                String inputVendor = Checker.getStringInput("Enter Vendor Name: ").toLowerCase();
                OutputSection.displayByVendor("", inputVendor);
                break;
            case 6:
                OutputSection.customSearch();
                break;
            case 7:
                System.out.println(ANSI_GREEN + "You selected Back" + ANSI_RESET);
                Ledger.showLedgerOptions(scanner, myLists);
            default:
                System.out.println(ANSI_YELLOW + "Invalid choice. Please select a valid option." + ANSI_RESET);
        }
    }

}
