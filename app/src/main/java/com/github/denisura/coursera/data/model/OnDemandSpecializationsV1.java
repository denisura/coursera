package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OnDemandSpecializationsV1 {

    @SerializedName("courseIds")
    @Expose
    public List<String> courseIds = new ArrayList<String>();
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("partnerIds")
    @Expose
    public List<Integer> partnerIds = new ArrayList<Integer>();
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("slug")
    @Expose
    public String slug;

    public List<String> partnerNames = new ArrayList<String>();


}