package com.mjearlb.api;

import com.google.gson.Gson;

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
    private static final String PHISH_NET_KEY = ".json?apikey=";

} // RequestPhishData
