package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class AccountingLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //taking user input

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
                    InputSection.makeDeposit();
                    break;
                case 'M':
                    InputSection.makePayment();
                    break;
                case 'L':
                    showLedgerOptions(scanner);
                    break;
                case 'X':
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void showLedgerOptions(Scanner scanner) {
        System.out.print("""
                             <<< LEDGER SCREEN >>>
                Please Choose an Option to Start:
                'A' All entries
                'D' Deposits
                'P' Payments
                'R' Reports
                'H' Home
                """);
        char userInput = scanner.next().charAt(0);
        List<Transaction> record = getTransaction();



    }
        public static List<Transaction> getTransaction(){
            String csvFileName = "transactions.csv";
            List<Transaction>transactions = new ArrayList<>();
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))){
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

        // Implement the logic for ledger options here
        //Read csv file grab the data - buffer.reader
        //create object Transaction     e.g   Transaction VariableName = new Transaction(data)
        // ArrayList<Transaction> res = new ArrayList()
        //res.
        //class DateComparator implements Comparator<Transaction> {
        //    @Override
        //    public int compare(Transaction t1, Transaction t2) {
        //        // Compare the date first
        //        int dateComparison = t1.getDate().compareTo(t2.getDate());
        //
        //        // If the dates are equal, compare the time
        //        if (dateComparison == 0) {
        //            return t1.getTime().compareTo(t2.getTime());
        //        }
        //
        //        return dateComparison;
        //    }
    }


