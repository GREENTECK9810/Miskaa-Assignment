package com.example.assignment.Api;

import com.example.assignment.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RegionApi {

    @GET("rest/v2/region/asia")
    Call<List<Country>> region();
}
