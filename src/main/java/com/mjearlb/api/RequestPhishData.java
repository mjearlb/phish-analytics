package com.mjearlb.api;

import com.google.gson.Gson;
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

import com.mjearlb.api.PhishNetApiKey;

public class RequestPhishData {
    
    /** Response from the Phish.net API request. */
    public class PhishData {
        boolean error;
        @SerializedName("error_message") String errorMessage;
        Show[] data;
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

} // RequestPhishData
