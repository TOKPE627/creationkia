package com.javatechie.awselasticbeanstalkexample.utility;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AppDates {
	
    public static Long	differenceInDays(String start_date, String end_date) throws ParseException{
    	 SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
             Date d1 = sdf.parse(start_date);
             Date d2 = sdf.parse(end_date);
   
             // Calucalte time difference
             // in milliseconds
             long difference_In_Time
                 = d2.getTime() - d1.getTime();
   
             long difference_In_Days
                 = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                   % 365;
         return difference_In_Days;
    }
    
    public static String currentDateTime() {
        LocalDateTime instance = LocalDateTime.now();
		 
		DateTimeFormatter formatter 
		    = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		return formatter.format(instance);
    }
}