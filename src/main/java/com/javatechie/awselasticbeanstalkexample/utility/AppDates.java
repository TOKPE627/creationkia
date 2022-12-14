package com.javatechie.awselasticbeanstalkexample.utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppDates {

    public static String currentDateTime() {
        LocalDateTime instance = LocalDateTime.now();
		 
		DateTimeFormatter formatter 
		    = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		return formatter.format(instance);
    }
}