package com.pluralsight;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

import static com.pluralsight.InputSection.scanner;

public class Checker {

    public static boolean isDateValid(String inputDate) {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatInThisForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String currentDateString = currentDate.format(formatInThisForm);
        String[] currentDateParts = currentDateString.split("/");

        if (inputDate.contains("-")) {
            return false;
        }

        String[] inputDateParts = inputDate.split("/");

        if (inputDateParts.length != 3) {
            return false;
        }

        for (String part : inputDateParts) {
            for (int i = 0; i < part.length(); i++) {
                if (!Character.isDigit(part.charAt(i)) || part.length() > 4) {
                    return false;
                }
            }
        }

        int inputDay = Integer.parseInt(inputDateParts[0]);
        int inputMonth = Integer.parseInt(inputDateParts[1]);
        int inputYear = Integer.parseInt(inputDateParts[2]);

        if (inputDay > 31 || inputDay == 0 || inputMonth > 12 || inputMonth == 0) {
            return false;
        }

        int currentYear = Integer.parseInt(currentDateParts[2]);
        int currentMonth = Integer.parseInt(currentDateParts[1]);
        int currentDay = Integer.parseInt(currentDateParts[0]);

        if (currentYear < inputYear || (currentYear == inputYear && currentMonth < inputMonth) || (currentYear == inputYear && currentMonth == inputMonth && currentDay < inputDay)) {
            System.out.println("Future transaction cannot be saved");
            return false;
        }

        return true;
    }

    public static boolean isTimeValid(String inputTime) {
        if (!inputTime.contains(":")) {
            return false;
        }

        String[] timeParts = inputTime.split(":");
        if (timeParts.length != 2) {
            return false;
        }

        try {
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);

            if (hours > 24 || minutes > 59) {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("There is a non-numeric character in your time input");
            return false;
        }

        return true;
    }
    public static boolean isAmountValid(double amount) {
        // Check if the amount is non-negative and has at most two decimal places
        return amount >= 0 && String.format("%.2f", amount).equals(String.valueOf(amount));
    }

    public static LocalDate getDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                // Use formatter when returning current date for consistency
               return LocalDate.now();
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate parsedDate = LocalDate.parse(userInput, formatter);
                LocalDate currentDate = LocalDate.now();

                if (parsedDate.isAfter(currentDate)) {
                    System.out.println("You entered a date in the future. Please enter a valid date.");
                } else {
                    return parsedDate;
                }
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime getTime(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                LocalTime now = LocalTime.now();
                String newNow = now.format(format);
                return LocalTime.parse(newNow, format);
            }

            try {
                return LocalTime.parse(userInput, format);
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    public static String getStringInput(String prompt) {
        String input;
        System.out.println(prompt);
        input = scanner.nextLine();

        // Allow spaces in the input
        while (!input.matches("^[a-zA-Z\\s]+$")) {
            System.out.println("Invalid input. Please enter only letters and spaces: ");
            input = scanner.nextLine();
        }

        return input;
    }

    public static LocalDate getDateCustom(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(userInput);
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    public static char getCharInput() {
        String userInput = scanner.nextLine().toUpperCase().trim();
        if (!userInput.isEmpty()) {
            return userInput.charAt(0);
        } else {
            return getCharInput();
        }
    }

    public static String getStringInputCustom(String prompt) { // For the amount entry
        String input;
        System.out.println(prompt);
        input = scanner.nextLine();

        // Prompts the user in custom search in case input is empty or non-numeric
        while (!input.isEmpty() && !isNumeric(input)) {
            System.out.println("Invalid input. Please enter a valid value: ");
            input = scanner.nextLine();
        }

        return input;
    }

    public static boolean isNumeric(String str) { // To evaluate in getStringInputCustom
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double getDoubleInput(String amountString) { // For main
        while (true) {
            if (amountString.isEmpty()) {
                System.out.println("Amount cannot be empty. Please enter a valid numeric amount.");
                amountString = getStringInputCustom("Enter an amount: ");
            } else {
                try {
                    return Double.parseDouble(amountString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric amount.");
                    amountString = getStringInputCustom("Enter an amount: ");
                }
            }
        }
    }

    public static double parseAmount(String amountString) { // Parse the amount from getStringInputCustom
        if (amountString.isEmpty()) {
            return 0;
        } else {
            try {
                return Double.parseDouble(amountString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                return parseAmount(getStringInputCustom("Enter an amount: "));
            }
        }
    }

    public static double getAmount(String prompt) {
        System.out.println(prompt);
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
        return amount;
    }
}




