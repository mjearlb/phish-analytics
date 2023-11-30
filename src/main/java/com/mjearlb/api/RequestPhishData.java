package com.mjearlb.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

import com.mjearlb.api.PhishNetApiKey;

/**
 * RequestPhishData allows for easy access to the Phish.net API.
 */
public class RequestPhishData {

    /** Response from the Phish.net API request. */
    public class PhishData {
        boolean error;
        @SerializedName("error_message") String errorMessage;
        Show[] data;

        @Override
        public String toString() {
            String str = ""; 

            for (int i = 0; i < this.data.length; i++) {
                str += this.data[i].showDate + ": " + this.data[i].venue + " - " + this.data[i].city + ", " + this.data[i].state + " " + this.data[i].country + "\n"; 
            } // for

            return str; 
        } // toString

        /** Sorts the data array by show date. */
        public void sortByShowDate() {
            Arrays.sort(data, Comparator.comparing(Show::getShowDate));
        } // sortByShowDate

    } // PhishData

    /** Data for each show from the Phish.net API. */
    public class Show {
        @SerializedName("showid") String showId;
        @SerializedName("showyear") String showYear;
        @SerializedName("showmonth") String showMonth;
        @SerializedName("showday") String showDay;
        @SerializedName("showdate") String showDate;
        @SerializedName("permalink") String permaLink;
        @SerializedName("exclude_from_stats") String excludeFromStats;
        @SerializedName("venueid") String venueId;
        @SerializedName("setlist_notes") String setlistNotes;
        String venue;
        String city;
        String state;
        String country;
        @SerializedName("artistid") String artistId;
        @SerializedName("artist_name") String artistName;
        @SerializedName("tourid") String tourId;
        @SerializedName("tour_name") String tourName;
        @SerializedName("created_at") String createdAt;
        @SerializedName("updated_at") String updatedAt;

        /** Returns the showDate var */
        public String getShowDate() {
            return showDate; 
        } // getShowDate
    } // Data

    /** For use with the Phish.net API. */
    private static final String PHISH_NET_API = "https://api.phish.net/v5/";
    private static final String PHISH_NET_KEY = ".json?apikey=" + PhishNetApiKey.PRIVATE_API_KEY;

    /** HTTP client. */
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    private static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    /**
     * Contact Phish.net API using HTTP GET request.
     *
     * @param requestType the type of request user wants to make to Phish.net.
     * @return phishData the data we recieve from our API request to Phish.net. 
     */
    public static PhishData contactPhishNet(String requestType) {
        String uri = PHISH_NET_API + requestType + PHISH_NET_KEY;
        PhishData phishData = null; 
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());

            // check request is okay
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if

            phishData = GSON
                .fromJson(response.body(), PhishData.class);

        } catch (IOException | InterruptedException e) {
            System.err.println(e);
            e.printStackTrace();
        } // try/catch

        return phishData;
    } // contactPhishNet

} // RequestPhishData
