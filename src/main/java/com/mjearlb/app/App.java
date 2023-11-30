package com.mjearlb.app;

import com.mjearlb.api.RequestPhishData;
import com.mjearlb.api.RequestPhishData.PhishData;;

/**
 * This app will allow for analysis of Phish.net
 * setlist data.
 */
public class App {
    public static void main(String[] args) {
        PhishData phishData = RequestPhishData.contactPhishNet("shows");
        phishData.sortByShowDate(); 
        printData(phishData); 
    } // main

    /** 
     * Prints the data we want printed to the terminal. 
     */
    public static void printData(PhishData phishData) {
        System.out.print(phishData); 
    } // printData
} // App
