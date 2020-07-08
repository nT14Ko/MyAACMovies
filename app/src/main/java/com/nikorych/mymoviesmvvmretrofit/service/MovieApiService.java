package com.nikorych.mymoviesmvvmretrofit.service;

import com.nikorych.mymoviesmvvmretrofit.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMovies(@Query("api_key") String apiKey);
}
