package com.github.denisura.coursera.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Linked {

    @SerializedName("courses.v1")
    @Expose
    public List<CoursesV1> coursesV1 = new ArrayList<CoursesV1>();
    @SerializedName("partners.v1")
    @Expose
    public List<PartnersV1> partnersV1 = new ArrayList<PartnersV1>();
    @SerializedName("onDemandSpecializations.v1")
    @Expose
    public List<OnDemandSpecializationsV1> onDemandSpecializationsV1 = new ArrayList<OnDemandSpecializationsV1>();

    private Map<Integer, PartnersV1> mPartnersMap;
    private Map<String, CoursesV1> mCoursesMap;
    private Map<String, OnDemandSpecializationsV1> mSpecializationMap;

    private void setPartners() {
        if (mPartnersMap == null) {
            mPartnersMap = new HashMap();
            for (PartnersV1 partner : partnersV1) {
                mPartnersMap.put(partner.id, partner);
            }
        }
    }

    public PartnersV1 getPartnerById(int id) {
        setPartners();
        return mPartnersMap.get(id);
    }

    private void setCourseMap() {
        if (mCoursesMap == null) {
            mCoursesMap = new HashMap();
            for (CoursesV1 course : coursesV1) {
                for (int parnerId : course.partnerIds) {
                    course.partnerNames.add(getPartnerById(parnerId).name);
                }
                mCoursesMap.put(course.id, course);
            }
        }
    }

    public CoursesV1 getCourseById(String id) {
        setCourseMap();
        return mCoursesMap.get(id);
    }

    private void setSpecializationMap() {
        if (mSpecializationMap == null) {
            mSpecializationMap = new HashMap();
            for (OnDemandSpecializationsV1 specialization : onDemandSpecializationsV1) {
                for (int parnerId : specialization.partnerIds) {
                    if (getPartnerById(parnerId) != null) {
                        specialization.partnerNames.add(getPartnerById(parnerId).name);
                    }
                }
                mSpecializationMap.put(specialization.id, specialization);
            }
        }
    }

    public OnDemandSpecializationsV1 getSpecializationById(String id) {
        setSpecializationMap();
        return mSpecializationMap.get(id);
    }

}