package com.mjearlb.app;

import com.mjearlb.api.RequestPhishData;

/**
 * This app will allow for analysis of Phish.net
 * setlist data.
 */
public class App {
    public static void main(String[] args) {
        RequestPhishData.contactPhishNet("shows");
    } // main
} // App
