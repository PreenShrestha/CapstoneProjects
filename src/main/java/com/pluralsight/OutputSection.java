package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        //System.out.println("PreviousMonth: " + previousMonth);
        //boolean isEmpty = true;
        for (Transaction transaction : transactions) {
            date = transaction.getDate().getMonthValue();
            date2 = now.minusMonths(1).getMonthValue();
            date2 = now.getMonthValue();
            year = transaction.getDate().getYear();
            year2 = now.getYear();
            if (date == date2 && year == year2) {
                printTransaction(transaction);

            }

        }
        // if (isEmpty) {
        //   System.out.println("No transactions on previous month");
        // }
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


    public static void displayByVendor(List<Transaction> myLists, String UserInput) {
        boolean found = false;
        for (int i = myLists.size() - 1; i >= 0; i--) {
            if (myLists.get(i).getVendor().equals(UserInput)) {
                String s = String.format("%-16s %-10s %-35s %-30s %.2f", myLists.get(i).getDate(), myLists.get(i).getTime(), myLists.get(i).getDescription(), myLists.get(i).getVendor(), myLists.get(i).getAmount());
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for the vendor: " + UserInput);
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
                transactions.add(new Transaction(LocalDate.parse(parts[0], formatting), time, description, vendor, amount));
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
