package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ReportPart {
    public static void showReportOptions(Scanner scanner, List<Transaction> myLists) {
        System.out.print("""
                             <<< REPORTS SCREEN >>>
                             
                Please choose an option to proceed:
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                6) Custom Search
                7) Back                          
                """);

        System.out.println("Please choose your option: ");
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
                System.out.println("You selected Back");
                Ledger.showLedgerOptions(scanner, myLists);
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

}
