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
        boolean repeat = Checker.checkDate(date);

        while(!repeat)
        {
            System.out.println("Invalid Input see Format and re- enter.");
            System.out.println("Enter the Date in DD/MM/YYYY format: ");
            date = scanner.nextLine();
            repeat = Checker.checkDate(date);
        }


        System.out.println("Enter the time (HH:mm): ");
        String time = scanner.nextLine();
        boolean rerun = Checker.checkTime(time);
        while(!rerun)
        {
            System.out.println("You have invalid input read format and re- enter");
            System.out.println("Enter the time in HH:mm format: ");
            time= scanner.nextLine();
            rerun = Checker.checkDate(time);
        }

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor name: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();
        double res = 0.00;
        boolean s = Checker.checkAmount(res);
        while(s) {
            try {
                res = scanner.nextDouble();
                scanner.nextLine();
                res = amount;
                if (amount >= 0) {
                    res = amount;
                    s = false; // Exit the loop when a non-negative amount is entered
                } else {
                    System.out.println("Amount cannot be negative. Re-enter a non-negative amount.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Re - enter");
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
            writer.write(writeLine + System.lineSeparator());
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
