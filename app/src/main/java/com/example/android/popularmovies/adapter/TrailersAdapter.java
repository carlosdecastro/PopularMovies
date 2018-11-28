package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;


//The adapter for the recyclerView

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private List<Trailer> mData;
    private LayoutInflater mInflater;
    private ListItemClickListener mOnClickListener;


    public TrailersAdapter(Context context, List<Trailer> data, TrailersAdapter.ListItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.trailers_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String path = mData.get(position).getKey();

        // We create the url where the thumbnail of the video is
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("img.youtube.com")
                .appendPath("vi")
                .appendPath(path)
                .appendEncodedPath("mqdefault.jpg");
        String myUrl = builder.build().toString();

        holder.name.setText(mData.get(position).getName());
        Picasso.with(mInflater.getContext())
                .load(myUrl)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();

    }

    public interface ListItemClickListener {
        void onListItemClick(Trailer trailer);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.trailerImageView);
            name = itemView.findViewById(R.id.name_value_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Trailer trailer = mData.get(getAdapterPosition());
            mOnClickListener.onListItemClick(trailer);

        }
    }

}

