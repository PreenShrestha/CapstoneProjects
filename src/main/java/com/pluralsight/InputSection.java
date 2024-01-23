package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class InputSection {

    static DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner scanner = new Scanner(System.in);
    public static void makeDeposit(List<Transaction> myLists)
    {
        LocalDate dateInput = Checker.getDate("Enter the date (DD/MM/YYYY) or press enter for automatic: ");
        LocalTime timeInput = Checker.getTime("Enter the time (HH:mm) or press enter for automatic");
        String description = Checker.getStringInput("Enter the description: ");
        String vendor = Checker.getStringInput("Enter the vendor name: ");
        double amount = Checker.getAmount("Enter the amount: ");
        writeToCSV(myLists, dateInput, timeInput, description, vendor, amount);
    }


    public static void makePayment(List<Transaction>myLists) { //this will all store in csv file
        LocalDate dateInput = Checker.getDate("Enter the date (DD/MM/YYYY) or press enter for automatic: ");
        LocalTime timeInput = Checker.getTime("Enter the time (HH:mm) or press enter for automatic");
        String description = Checker.getStringInput("Enter the description: ");
        String vendor = Checker.getStringInput("Enter the vendor name: ");
        double amount = Checker.getAmount("Enter the amount: ");
        writeToCSV(myLists, dateInput, timeInput, description, vendor, -amount);
    }

    private static void writeToCSV(List<Transaction> myLists, LocalDate dateInput, LocalTime timeInput, String description, String vendor, double amount) {
        try { // handling potential errors in the code that follows.
            FileWriter writer = new FileWriter("Transactions.csv", true);
            //used to write data to a file called "Transactions.csv."
            // new data will be appended to the end of the file, rather than overwriting its contents.
            String s1 = String.format("%s|%s|%s|%s|$%.2f", dateInput.format(formatting), timeInput, description, vendor, amount); //formatting the data
            writer.write(s1 + System.lineSeparator()); //used to write the formatted string s1
            writer.close(); //ensure that the file is properly saved
            System.out.println("Deposit recorded successfully.");
        }
        catch(IOException e) //This line starts a catch block to handle any IOException that may occur within the try block.
        {
            System.out.println("Invalid- File");
        }

        myLists.add(new Transaction(dateInput, timeInput, description, vendor, amount));
    }

}
