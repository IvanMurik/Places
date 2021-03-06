package com.murik.places.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.murik.places.R;
import com.murik.places.model.Result;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> {

    Context context;
    private List<Result> placeResult;
    String photoReference = "";

    public PlacesAdapter(Context context, List<Result> results) {
        this.context = context;
        placeResult = results;
    }

    @Override
    public PlacesAdapter.PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_place, parent, false);
        return new PlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlacesAdapter.PlacesViewHolder holder, int position) {
        holder.bind(placeResult.get(position));
        if(placeResult.get(position).getPhotos() != null){
            photoReference = placeResult.get(position).getPhotos().get(0).getPhotoReference();
        }
        //https://farm5.static.flickr.com/4115/5394079747_834307b7a2_b.jpg
        if(context != null){
            Glide.with(context)
                    .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=150&maxhight=150&photoreference="
                            + photoReference +"&key=AIzaSyAjZVQoj8mKQDalDGHG8D1JNNSQA_rQKcs")
                    .into(holder.imgPlace);
        }

    }

    @Override
    public int getItemCount() {
        return placeResult.size();
    }


    public class PlacesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNamePlace;
        private TextView tvVicinity;
        private CheckBox cbOpenNow;
        private ImageView imgPlace;


        public PlacesViewHolder(View itemView) {
            super(itemView);
            tvNamePlace = itemView.findViewById(R.id.tvNamePlace);
            tvVicinity = itemView.findViewById(R.id.tvVicinity);
            cbOpenNow = itemView.findViewById(R.id.cbOpenNow);
            imgPlace = itemView.findViewById(R.id.imgPlace);

        }

        public void bind(Result result) {
            tvNamePlace.setText(result.getName());
            tvVicinity.setText(result.getVicinity());
            if (result.getOpeningHours() != null) {
                cbOpenNow.setChecked(result.getOpeningHours().getOpenNow());
            }
        }
    }
}
