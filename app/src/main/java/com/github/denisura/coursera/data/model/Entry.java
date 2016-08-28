package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("onDemandSpecializationId")
    @Expose
    public String onDemandSpecializationId;
    @SerializedName("courseId")
    @Expose
    public String courseId;
}