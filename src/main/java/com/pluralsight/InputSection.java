package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InputSection {
    static Scanner scanner = new Scanner(System.in);
    public static void makeDeposit(List<Transaction> myLists)
    {

        System.out.println("Enter the date YYYY/MM/DD: ");
        String date = scanner.nextLine();

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
