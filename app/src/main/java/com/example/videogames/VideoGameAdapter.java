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
    private OnClickListener onClickListener;

    public VideoGameAdapter(Context context, OnClickListener onClickListener) {
        data = new ArrayList<>();

        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public VideoGameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new VideoGameHolder(view, onClickListener);
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

    public void limpiar() {
        data.clear();
    }

    public interface OnClickListener{
        void onClick(int position);
    }
}
