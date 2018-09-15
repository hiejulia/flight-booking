package com.project.flightbooking.main;

public class Data {

    @SerializedName("alertTopic")
    @Expose
    private String alertTopic;

    @SerializedName("alertMessage")
    @Expose
    private String alertMessage;
}
