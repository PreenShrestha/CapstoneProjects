package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class OutputSection {
    public static void displayAllTransactions(List<Transaction> lists) {
        System.out.print("""
                                   <<< All Transactions >>>
                                       
                """);

        for (int i=lists.size() -1; i >= 0; i--) {
            Transaction transaction = lists.get(i);
            System.out.printf("%-15s %-10s %-35s %-20s $%.02f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
        //Transaction t[i]
        //t[i].getDate(), getTime(),......
    }

    public static void displayAllDeposits(List<Transaction> lists) {
        System.out.print(""" 
                              <<< All Deposit Transactions >>>
                             
             """);
        System.out.printf("%-15s %-10s %-35s %-20s %9s%n", "Date", "Time", "Description", "Vendor", "Amount");

        for (int i = lists.size()-1; i>=0; i--) {
            Transaction transaction = lists.get(i);
            if (transaction.getAmount() < 0) { // Check if the amount is negative (indicating a deposit)
                System.out.printf("%-15s %-10s %-35s %-20s $%.2f%n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
    }


    public static void displayAllPayment(List<Transaction> lists) //create function for displaying all
    {
        System.out.print("""
                                   <<< All Payments Transactions >>>
                                       
                """);
        System.out.printf("%-15s %-10s %-35s %-20s %-10s%n", "Date", "Time", "Description", "Receiver", "Amount");

        for (int i = lists.size() - 1; i >= 0; i--) {
            Transaction transaction = lists.get(i);
            if (transaction.getAmount() < 0) {
                String s = String.format("%-16s %-10s %-35s %-30s $%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), -transaction.getAmount());
            }
        }
    }


        //reports

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
