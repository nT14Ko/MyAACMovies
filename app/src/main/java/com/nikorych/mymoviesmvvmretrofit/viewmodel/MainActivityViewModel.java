package com.nikorych.mymoviesmvvmretrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.nikorych.mymoviesmvvmretrofit.model.MovieDataSource;
import com.nikorych.mymoviesmvvmretrofit.model.MovieDataSourceFactory;
import com.nikorych.mymoviesmvvmretrofit.model.MovieRepository;
import com.nikorych.mymoviesmvvmretrofit.model.Result;
import com.nikorych.mymoviesmvvmretrofit.service.MovieApiService;
import com.nikorych.mymoviesmvvmretrofit.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        MovieApiService movieApiService = RetrofitInstance.getService();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application, movieApiService);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10).setPageSize(20).setPrefetchDistance(3).build();
        executor = Executors.newCachedThreadPool();
        pagedListLiveData = new LivePagedListBuilder<Long, Result>(movieDataSourceFactory, config).setFetchExecutor(executor).build();

    }
    public LiveData<List<Result>> getAllMovieData(){
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
