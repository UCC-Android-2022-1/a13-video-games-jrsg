package com.example.videogames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoGameAdapter extends RecyclerView.Adapter<VideoGameHolder> {
    private ArrayList<VideoGame> data;
    private Context context;

    public VideoGameAdapter(Context context) {
        data = new ArrayList<>();

        this.context = context;
    }

    @NonNull
    @Override
    public VideoGameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(android.R.layout.simple_list_item_1, parent);
        return new VideoGameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGameHolder holder, int position) {
        VideoGame videoGame = data.get(position);

        holder.getTextView().setText( videoGame.getNombre() );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void agregar(VideoGame videoGame) {
        data.add( videoGame );
        notifyDataSetChanged();
    }

    public VideoGame leer(int posicion){
        return data.get(posicion);
    }

    public interface OnClickListener{
        void onClick(int position);
    }
}
