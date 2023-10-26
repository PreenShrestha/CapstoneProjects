package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class AccountingLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //taking user input

        List<Transaction> myLists = getTransaction(); //Load initial data


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
                    showLedgerOptions(scanner, myLists);
                    break;
                case 'X':
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

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
                showReportOptions(scanner, myLists);
                break;
            case 'H':
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }

    }

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
                displayMonthToDate(myLists, currentDate);
                break;
            case 2:
                displayPreviousMonth(myLists, currentDate);
                break;
            case 3:
                displayYearToDate(myLists, currentDate);
                break;
            case 4:
                displayPreviousYear(myLists, currentDate);
                break;
            case 5:
                displayByVendor(myLists, scanner);
                break;
            case 6:
                System.out.println("You selected Back");
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    public static void displayMonthToDate(List<Transaction> myLists, String currentDate) {
        boolean isEmpty = true;
        for (int i = myLists.size() - 1; i >= 0; i--) {
            if (myLists.get(i).getDate().substring(3).equals(currentDate.substring(3))) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("No transactions for this month");
        }
    }

    public static void displayPreviousMonth(List<Transaction> myLists, String currentDate) {
        int currentMonth = Integer.parseInt(currentDate.substring(3, 5));
        String previousMonth = String.format("%02d/%s", currentMonth - 1, currentDate.substring(6));
        System.out.println("PreviousMonth: " + previousMonth);
        boolean isEmpty = true;
        for (int i = myLists.size() - 1; i >= 0; i--) {
            if (myLists.get(i).getDate().substring(3).equals(previousMonth)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }

        }
        if (isEmpty) {
            System.out.println("No transactions on previous month");
        }
    }

    public static void displayYearToDate(List<Transaction> myLists, String currentDate) {
        String currentYear = currentDate.substring(6);
        boolean isEmpty = true;
        for (int i = myLists.size() - 1; i >= 0; i--) {
            if (myLists.get(i).getDate().substring(6).equals(currentYear)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("No transactions for this year.");
        }
    }

    public static void displayPreviousYear(List<Transaction> myLists, String currentDate) {
        int currentYear = Integer.parseInt(currentDate.substring(6));
        String previousYear = "" + (currentYear - 1);
        System.out.println("PreviousYear: " + previousYear);
        boolean isEmpty = true;
        for (int i = myLists.size() - 1; i >= 0; i--) {
            if (myLists.get(i).getDate().substring(6).equals(previousYear)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("You do not have any transactions on previous year");
        }
    }

    public static void displayByVendor(List<Transaction> myLists, Scanner scanner) {
        displayVendorOptions(myLists);
        System.out.println("Enter the vendor name: ");
        String vendorInput = scanner.nextLine();
        vendorInput = scanner.nextLine();
        for (int i = myLists.size() - 1; i >= 0; i--) {
            if (myLists.get(i).getVendor().equals(vendorInput)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
            }
        }


    }

    public static void displayVendorOptions(List<Transaction> myLists) {
        System.out.println("Following vendor are appear in your ledger: ");
        StringBuilder res = new StringBuilder();
        HashSet<String> s = new HashSet<>();
        for (Transaction myList : myLists) {
            if (!s.contains(myList.getVendor())) {
                String Add = myList.getVendor() + "      ";
                res.append(Add);
                s.add(myList.getVendor());
            }
        }
        System.out.println(res.toString());
    }

}

    public static List<Transaction> getTransaction() {
        String csvFileName = "transactions.csv";
        List<Transaction> transactions = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                String amt = parts[4].substring(1); // "$40.67" - > "40.67"
                double amount = Double.parseDouble(amt); //-> 40.67
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;


    }
}

class sortMyLists implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // Compare the date first
        int dateComparison = t1.getDate().compareTo(t2.getDate());
        // If the dates are equal, compare the time
        if (dateComparison == 0) {
            return t1.getTime().compareTo(t2.getTime());
        }

        return dateComparison;
    }
}




