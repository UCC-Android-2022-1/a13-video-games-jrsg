package com.example.videogames;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoGameHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public VideoGameHolder(@NonNull View itemView, VideoGameAdapter.OnClickListener onClickListener) {
        super(itemView);

        textView = itemView.findViewById(android.R.id.text1);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                onClickListener.onClick(position);
            }
        });
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
