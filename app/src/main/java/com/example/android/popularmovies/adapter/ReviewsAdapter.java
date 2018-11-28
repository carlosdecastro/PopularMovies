package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.model.Review;

import java.util.List;


//The adapter for the recyclerView

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<Review> mData;
    private LayoutInflater mInflater;


    public ReviewsAdapter(Context context, List<Review> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.reviews_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText("Review by " + mData.get(position).getAuthor());
        holder.content.setText(mData.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView content;
        private TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content_value_text_view);
            name = itemView.findViewById(R.id.name_value_text_view);
        }

    }

}

