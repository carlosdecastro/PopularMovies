package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.util.Helper;
import com.squareup.picasso.Picasso;

import java.util.List;


//The adapter for the recyclerView

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> mData;
    private LayoutInflater mInflater;
    private ListItemClickListener mOnClickListener;


    public interface ListItemClickListener {
        void onListItemClick (int clickedItemIndex);
    }

    public MoviesAdapter(Context context, List<Movie> data, ListItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movies_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String path = mData.get(position).getPosterPath();

        Picasso.with(mInflater.getContext()).load(Helper.imageUrl(path)).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setMovies(List<Movie> movies) {
        mData = movies;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.ivPoster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            //int elementId = mData.get(getAdapterPosition()).getId();

            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}
