package com.javatechie.awselasticbeanstalkexample.utility;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class AppHosts {
    public static String currentHostIpAddress() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }

}