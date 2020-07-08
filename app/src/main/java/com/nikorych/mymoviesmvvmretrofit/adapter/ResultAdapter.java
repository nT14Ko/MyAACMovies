package com.nikorych.mymoviesmvvmretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nikorych.mymoviesmvvmretrofit.R;
import com.nikorych.mymoviesmvvmretrofit.databinding.ResultListItemBinding;
import com.nikorych.mymoviesmvvmretrofit.model.Result;
import com.nikorych.mymoviesmvvmretrofit.view.MovieDetailsActivity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ResultAdapter extends PagedListAdapter<Result, ResultAdapter.ResultViewHolder> {

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ResultListItemBinding resultListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.result_list_item, parent, false);
        return new ResultViewHolder(resultListItemBinding);
    }

    public ResultAdapter(Context context) {
        super(Result.CALLBACK);
        this.context = context;
//        this.results = results;
    }

//    @Override
//    public int getItemCount() {
//        return results.size();
//    }

    private Context context;
//    private ArrayList<Result> results;

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
//        Result result = results.get(position);
        Result result = getItem(position);
        holder.resultListItemBinding.setResult(result);
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        private ResultListItemBinding resultListItemBinding;
        public ResultViewHolder(@NonNull ResultListItemBinding resultListItemBinding) {
            super(resultListItemBinding.getRoot());
            this.resultListItemBinding = resultListItemBinding;

            resultListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
//                        Result result = results.get(position);
                        Result result = getItem(position);
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }

}
