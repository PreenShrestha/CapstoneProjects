package com.pluralsight;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


}




