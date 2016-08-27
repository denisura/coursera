package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName("score")
    @Expose
    public Float score;
    @SerializedName("resourceName")
    @Expose
    public String resourceName;
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