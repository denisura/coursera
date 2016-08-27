package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Element {

    @SerializedName("entries")
    @Expose
    public List<Entry> entries = new ArrayList<Entry>();
    @SerializedName("id")
    @Expose
    public String id;

}