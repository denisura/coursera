package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    @SerializedName("elements")
    @Expose
    public List<Element> elements = new ArrayList<>();
    @SerializedName("linked")
    @Expose
    public Linked linked;

    public List<Object> entries = new ArrayList<>();

    public List<Object> getEntities() {
        for (Entry entry : elements.get(0).entries) {
            if (entry.onDemandSpecializationId != null) {
                if (linked.getSpecializationById(entry.id) != null) {
                    entries.add(linked.getSpecializationById(entry.id));
                }
                continue;
            }
            if (entry.courseId != null) {
                if (linked.getCourseById(entry.id) != null) {
                    entries.add(linked.getCourseById(entry.id));
                }
            }
        }
        return entries;
    }
}