package com.nikorych.mymoviesmvvmretrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nikorych.mymoviesmvvmretrofit.model.MovieRepository;
import com.nikorych.mymoviesmvvmretrofit.model.Result;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }
    public LiveData<List<Result>> getAllMovieData(){
        return movieRepository.getMutableLiveData();
    }
}
