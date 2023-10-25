package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class AccountingLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //taking user input

        System.out.println("Welcome to Your Financial Tracker Application.");

        while (true) { //display homeScreen
            System.out.print("""
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
                    makeDeposit(scanner);
                    break;
                case 'M':
                    makePayment(scanner);
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

    public static void makeDeposit(Scanner scanner) {
        System.out.println("Enter the date YYYY/MM/DD: ");
        String date = scanner.nextLine();
        date = scanner.nextLine();

        System.out.println("Enter the time HH:mm: ");
        String time = scanner.nextLine();

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();

        try { // handling potential errors in the code that follows.
            FileWriter writer = new FileWriter("Transactions.csv", true);
            //used to write data to a file called "Transactions.csv."
            // new data will be appended to the end of the file, rather than overwriting its contents.
            String s1 = String.format("%s|%s|%s|%s|$%.2f",date,time,description,vendor,amount); //formatting the data
            writer.write(s1); //used to write the formatted string s1
            writer.close(); //ensure that the file is properly saved
        }
        catch(IOException e) //This line starts a catch block to handle any IOException that may occur within the try block.
        {
            System.out.println("Invalid- File");
        }

    }

    public static void makePayment(Scanner scanner) { //this will all store in csv file
        System.out.println("Enter the date YYYY/MM/DD: ");
        String date = scanner.nextLine();

        System.out.println("Enter the time: ");
        String time = scanner.nextLine();

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the receiver name: ");
        String receiver = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();

        try{
            FileWriter writer = new FileWriter("Transaction.csv");
            String writeLine = String.format("%s|%s|%s|%s|-$%.2f",date,time,description,receiver,amount);
            writer.write(writeLine);
            writer.close();

        }
        catch(IOException e)
        {
            System.out.println("ERROR- Invalid file");
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


