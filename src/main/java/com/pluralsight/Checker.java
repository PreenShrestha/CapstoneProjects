package com.pluralsight;

import java.io.IOError;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Checker {

    public static boolean checkDate(String date)
    {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatInThisForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String cD = currentDate.format(formatInThisForm);
        String [] cDArray = cD.split("/");

        if(date.contains("-"))
        {
            return false;
        }
        String [] s = date.split("/");

        if(s.length != 3 ) // should have 3 value with date month and year more than that invalid input
        {
            return false;
        }

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                if (!Character.isDigit(s[i].charAt(j))) {
                    return false; // If any character is not a digit, it's an invalid date
                }
            }
        }

        if (Integer.parseInt(s[0]) > 31 || Integer.parseInt(s[0]) == 0) //In month there cannot be more than 31 days
        {
            return false;
        }
        if (Integer.parseInt(s[1])>12 || Integer.parseInt(s[1])==0)// In year there cannot be more than 12 months
        {
            return false;
        }

        //Checking for future time

        // dd/mm/yyyy -> 2023 entry -> 2023 < false
        if(Integer.parseInt(cDArray[2]) < Integer.parseInt(s[2]) || Integer.parseInt(cDArray[2]) == Integer.parseInt(s[2]) && Integer.parseInt(cDArray[1]) < Integer.parseInt(s[1]) ||
                Integer.parseInt(cDArray[1]) == Integer.parseInt(s[1]) && Integer.parseInt(cDArray[0])< Integer.parseInt(s[0]))
        {
            System.out.println("Future transaction cannot be saved");
            return false;

        }


        return true;
    }

    public static boolean checkTime(String time)
    {
        if(!time.contains(":"))
        {
            return false;
        }
        String [] t = time.split(":");
        if(t.length != 2) // We are taking only Hours and min
        {
            return false;
        }
        try {
            if (Integer.parseInt(t[0]) > 24 ) // hours
            {
                return false;
            }
            if(Integer.parseInt(t[1]) > 59)
            {
                return false;
            }
        }
        catch (IOError error){
            System.out.println("There is character in your output");
            return false;
        }
        return true;
    }

    public static boolean checkAmount(double amt)
    {
        if(amt<0)
        {
            return false;
        }
        return true;
    }
}
