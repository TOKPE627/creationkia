package com.javatechie.awselasticbeanstalkexample.utility;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AppHosts {
    public static String currentHostIpAddress() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }
    
    public static double screenWidth() {
        System.setProperty("java.awt.headless", "false");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        return width;
    }
   
}