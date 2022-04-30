package com.example.videogames;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoGameHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public VideoGameHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(android.R.id.text1);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
