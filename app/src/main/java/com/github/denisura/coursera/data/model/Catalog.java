package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class Catalog {

    @SerializedName("elements")
    @Expose
    public List<Element> elements = new ArrayList<>();
    @SerializedName("linked")
    @Expose
    public Linked linked;

    public List<Object> entries = new ArrayList<>();

    public List<Object> getEntities() {
        Timber.d("getEntities");

        for (Entry entry : elements.get(0).entries) {
            Timber.d("getEntities:entry:id %s", entry.id);
            if (entry.onDemandSpecializationId != null) {
                Timber.d("getEntities:entry:onDemandSpecializationId %s", entry.onDemandSpecializationId);
                if (linked.getSpecializationById(entry.id) != null) {
                    entries.add(linked.getSpecializationById(entry.id));
                }
                continue;
            }
            if (entry.courseId != null) {
                Timber.d("getEntities:entry:courseId %s", entry.courseId);
                if (linked.getCourseById(entry.id) != null) {
                    entries.add(linked.getCourseById(entry.id));
                }
            }
        }
        return entries;
    }
}