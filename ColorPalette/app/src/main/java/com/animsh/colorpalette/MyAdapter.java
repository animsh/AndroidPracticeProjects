package com.animsh.colorpalette;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;

import java.text.MessageFormat;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    List<ColorItem> colorItemList;
    Context mContext;

    public MyAdapter() {
    }

    public MyAdapter(List<ColorItem> colorItemList, Context mContext) {
        this.colorItemList = colorItemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(mContext).inflate(
                R.layout.item_color,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(colorItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return colorItemList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView color, population, bodyText;
        LinearLayout itemColor;
        ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            color = itemView.findViewById(R.id.color_color);
            population = itemView.findViewById(R.id.color_population);
            bodyText = itemView.findViewById(R.id.color_body_text);
            itemColor = itemView.findViewById(R.id.item_color);
            imageView = itemView.findViewById(R.id.imageview);
        }

        public void setData(ColorItem colorItem) {
            color.setText(MessageFormat.format("Color: {0}", colorItem.getColor()));
            population.setText(MessageFormat.format("Population: {0}", colorItem.getPopulation()));
            bodyText.setText(MessageFormat.format("Body Text: {0}", colorItem.getBodyTextColor()));
           /* Glide.with(mContext).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
                    .listener(GlidePalette.with("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
                            .use(GlidePalette.Profile.MUTED)
                            .intoBackground(itemColor)
                            .intoTextColor(color)
                            *//*.use(GlidePalette.Profile.VIBRANT_DARK)
                            .intoBackground(population)
                            .intoTextColor(color)
                            .crossfade(true)*//*
                    )
                    .into(imageView);*/
            itemColor.setBackgroundColor(Integer.parseInt(colorItem.getColor()));
        }
    }

}
