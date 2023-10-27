package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class InputSection {
    static Scanner scanner = new Scanner(System.in);
    public static void makeDeposit(List<Transaction> myLists)
    {

        System.out.println("Enter the date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        boolean repeat = Checker.isDateValid(date);

        while(!repeat)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            date = scanner.nextLine();
            repeat = Checker.isDateValid(date);
        }


        System.out.println("Enter the time (HH:mm): ");
        String time = scanner.nextLine();
        boolean rerun = Checker.isTimeValid(time);

        while(!rerun)
        {
            System.out.println("You have invalid input read format and re- enter");
            System.out.println("Enter the time in HH:mm format: ");
            time= scanner.nextLine();
            rerun = Checker.isTimeValid(time);
        }

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double amount = 0.00;
        boolean validAmount = false;

        while(!validAmount) {
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();
                validAmount = Checker.isAmountValid(amount);
                if (amount >= 0) {
                    validAmount = true;
                } else {
                    System.out.println("Amount cannot be negative. Re-enter a non-negative amount.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please re-enter the amount.");
                scanner.nextLine();
            }
        }


        try { // handling potential errors in the code that follows.
            FileWriter writer = new FileWriter("Transactions.csv", true);
            //used to write data to a file called "Transactions.csv."
            // new data will be appended to the end of the file, rather than overwriting its contents.
            String s1 = String.format("%s|%s|%s|%s|$%.2f",date,time,description,vendor,amount); //formatting the data
            writer.write(s1 + System.lineSeparator()); //used to write the formatted string s1
            writer.close(); //ensure that the file is properly saved
            System.out.println("Deposit recorded successfully.");
        }
        catch(IOException e) //This line starts a catch block to handle any IOException that may occur within the try block.
        {
            System.out.println("Invalid- File");
        }

        myLists.add(new Transaction(date, time, description, vendor, amount));


    }

    public static void makePayment(List<Transaction>myLists) { //this will all store in csv file
        System.out.println("Enter the date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        boolean repeat = Checker.isDateValid(date);

        while (!repeat) {
            System.out.println("Invalid Input. Please see the format and re-enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            date = scanner.nextLine();
            repeat = Checker.isDateValid(date);
        }

        System.out.println("Enter the time (HH:mm): ");
        String time = scanner.nextLine();
        boolean rerun = Checker.isTimeValid(time);

        while (!rerun) {
            System.out.println("You have entered an invalid time format. Please re-enter.");
            System.out.println("Enter the time in HH:mm format: ");
            time = scanner.nextLine();
            rerun = Checker.isTimeValid(time);
        }

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor name: ");
        String receiver = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double amount = 0.00;

        while (amount <= 0) {
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount <= 0) {
                    System.out.println("Amount must be a positive number. Re-enter the amount.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please re-enter the amount.");
                scanner.nextLine();
            }
        }
        if (amount > 0) {
            Transaction payment = new Transaction(date, time, description, receiver, amount);
            myLists.add(payment);
            // Code to save the transaction to the CSV file.
        }


        try{
            FileWriter writer = new FileWriter("Transactions.csv", true);
            String s1 = String.format("%s|%s|%s|%s|-$%.2f",date,time,description,receiver,amount);
            writer.write(s1 + System.lineSeparator());
            writer.close();
            System.out.println("Payment recorded successfully.");

        }
        catch(IOException e)
        {
            System.out.println("ERROR- Invalid file");
        }

        myLists.add(new Transaction(date, time, description, receiver, -amount));

    }

}
