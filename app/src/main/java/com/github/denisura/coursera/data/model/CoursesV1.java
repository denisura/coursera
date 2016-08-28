package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CoursesV1 {

    @SerializedName("photoUrl")
    @Expose
    public String photoUrl;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("partnerIds")
    @Expose
    public List<Integer> partnerIds = new ArrayList<Integer>();
    @SerializedName("name")
    @Expose
    public String name;
    public List<String> partnerNames = new ArrayList<String>();
}