package com.github.denisura.coursera.data.network;


import com.github.denisura.coursera.data.model.Catalog;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CourseraApi {

    String BASE_URL = "https://www.coursera.org/";

    @GET("/api/catalogResults.v2?q=search&fields=courseId,onDemandSpecializationId,courses.v1(name,photoUrl,partnerIds),onDemandSpecializations.v1(name,logo,courseIds,partnerIds),partners.v1(name)&includes=courseId,onDemandSpecializationId,courses.v1(partnerIds)")
    Observable<Catalog> searchCatalog(
            @Query("query") String query,
            @Query("start") int start,
            @Query("limit") int limit
    );
}