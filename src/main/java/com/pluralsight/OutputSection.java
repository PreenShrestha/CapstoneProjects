package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class OutputSection {

    //Ledger Section Display
    public static void displayAllTransactions(List<Transaction> lists) {
        System.out.print("""
                                   <<< All Transactions >>>
                                       
                """);
        System.out.printf("%-15s %-10s %-35s %-20s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");

        for (int i = lists.size() - 1; i >= 0; i--) {
            Transaction transaction = lists.get(i);
            System.out.printf("%-15s %-10s %-35s %-20s $%.02f%n",
                    transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
    }

    public static void displayAllDeposits(List<Transaction> lists) {
        System.out.print("""
                             <<< All Deposit Transactions >>>
                        
                """);
        System.out.printf("%-15s %-10s %-35s %-20s %10s%n", "Date", "Time", "Description", "Vendor", "Amount");

        for (Transaction transaction : lists) {
            if (transaction.isDeposit()) {
                System.out.printf("%-15s %-10s %-35s %-20s $%.2f%n",
                        transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
    }

    public static void displayAllPayment(List<Transaction> lists) {
        System.out.print("""
                             <<< All Payments Transactions >>>
                        
                """);
        System.out.printf("%-15s %-10s %-35s %-20s %10s%n", "Date", "Time", "Description", "Receiver", "Amount");

        for (Transaction transaction : lists) {
            if (transaction.isPayment()) {
                System.out.printf("%-15s %-10s %-35s %-20s $%.2f%n",
                        transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
    }

    //ReportSection Display

    public static List<Transaction> transactions = getTransaction();
    static LocalDate now = LocalDate.now();
    static int date;
    static int date2;
    static int year;
    static int year2;

    public static void displayMonthToDate() {
        for (Transaction transaction : transactions) {
            date = transaction.getDate().getMonthValue();
            date2 = now.getMonthValue();
            year = transaction.getDate().getYear();
            year2 = now.getYear();
            if (date == date2 && year == year2) {
                printTransaction(transaction);
            }
        }
    }

    public static void displayPreviousMonth() {
        for (Transaction transaction : transactions) {
            date = transaction.getDate().getMonthValue();
            date2 = now.minusMonths(1).getMonthValue();
            year = transaction.getDate().getYear();
            year2 = now.getMonthValue() == 1 ? now.minusYears(1).getYear() : now.getYear();
            if (date == date2 && year == year2) {
                printTransaction(transaction);
            }
        }
    }

    public static void displayYearToDate() {
        for (Transaction transaction : transactions) {
            date = transaction.getDate().getYear();
            date2 = now.getYear();

            if (date == date2) {
                printTransaction(transaction);
            }
        }
    }

    public static void displayPreviousYear() {
        for(Transaction transaction : transactions) {
            date = transaction.getDate().getYear();
            date2 = now.minusYears(1).getYear();

            if (date == date2) {
                printTransaction(transaction);
            }
        }
    }


    public static void displayByVendor(String filter, String vendor) {
        if (!vendor.isEmpty()) {
            filter = vendor;
        }
        for (Transaction transaction : transactions
             ) {
            if (transaction.getVendor().toLowerCase().contains(vendor)) {
                printTransaction(transaction);
            }
        }
    }

    public static void customSearch() {
        // Custom Search
        System.out.println("Custom Search: ");
        LocalDate startDate = Checker.getDateCustom("Enter a start date in (YYYY-MM-DD) or leave blank");
        LocalDate endDate = Checker.getDateCustom("Enter a end date in (YYYY-MM-DD) or leave blank");
        String description = Checker.getStringInput("Enter a description: ").toLowerCase();
        String vendor = Checker.getStringInput("Enter a vendor: ").toLowerCase();
        String amountString = Checker.getStringInputCustom("Enter an amount: "); // Evaluate input to String for isEmpty
        double amount = Checker.parseAmount(amountString); // Evaluate if amount is Empty.


        System.out.print("\u001B[1m");
        System.out.printf("%40s\n", "CUSTOM SEARCH RESULT");
        System.out.print("\u001B[0m");
//        printTransactionTitle();

        boolean ifFound = false; // Evaluate if there will be a result

        for (Transaction transaction : transactions
        ) {
            boolean isStartDate = startDate == null || !transaction.getDate().isBefore(startDate); // Evaluates transaction date is not before the startDate or if date is null
            boolean isEndDate = endDate == null || !transaction.getDate().isAfter(endDate); // Evaluates transactions date is not after the endDate or if date is null
            boolean isDescription = transaction.getDescription().toLowerCase().contains(description);
            boolean isVendor = transaction.getVendor().toLowerCase().contains(vendor);
            boolean isAmount = transaction.getAmount() == amount || amount == 0;
            if (isStartDate && isEndDate && isDescription && isVendor && isAmount) {
                printTransaction(transaction);
                ifFound = true;
            }
        }
        if(!ifFound){
            System.out.println("We couldn't find that. Sorry.");
        }
    }


    public static List<Transaction> getTransaction() {
        String csvFileName = "Transactions.csv";
        List<Transaction> transactions = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                String amt;
                if (parts[4].charAt(0) == '-') {
                    //-$455.00 -> -455.00
                    amt = parts[4].substring(0, 1) + parts[4].substring(2);

                } else {
                    amt = parts[4].substring(1); // "$40.67" - > "40.67"
                }
                double amount = Double.parseDouble(amt); //-> 40.67 // -$456
                DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                transactions.add(new Transaction(LocalDate.parse(parts[0], formatting), LocalTime.parse(parts[1]), description, vendor, amount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Comparator<Transaction> compareByDate = Comparator.comparing(Transaction::getDate).reversed();
        Comparator<Transaction> compareByTime = Comparator.comparing(Transaction::getTime).reversed();
        transactions.sort(compareByDate.thenComparing(compareByTime));

        return transactions;

// -
    }

    public static void printTransaction(Transaction transaction) { // To print data
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("%-15s %-15s %-30s %-25s %15.2f\n",
                transaction.getDate().format(formatting),
                transaction.getTime(),
                transaction.getDescription(),
                transaction.getVendor(),
                transaction.getAmount()
        );
    }

}
