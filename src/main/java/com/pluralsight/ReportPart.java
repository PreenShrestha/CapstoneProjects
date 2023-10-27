package com.pluralsight;

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
                6) Back
                                          
                """);

        System.out.println("Please choose your option: ");
        int usersInput = scanner.nextInt();

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDate = currentTime.format(formatting);

        switch (usersInput) {
            case 1:
                OutputSection.displayMonthToDate(myLists, currentDate);
                break;
            case 2:
                OutputSection.displayPreviousMonth(myLists, currentDate);
                break;
            case 3:
                OutputSection.displayYearToDate(myLists, currentDate);
                break;
            case 4:
                OutputSection.displayPreviousYear(myLists, currentDate);
                break;
            case 5:
                System.out.println("Enter the vendor name (or type 'back' to go back): ");
                String vendorInput = scanner.nextLine();
                if (!vendorInput.equalsIgnoreCase("back")) {
                    OutputSection.displayByVendor(myLists, scanner);
                }
                break;
            case 6:
                System.out.println("You selected Back");
                Ledger.showLedgerOptions(scanner, myLists);
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

}
