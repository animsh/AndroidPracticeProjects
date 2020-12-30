package com.animsh.colorpalette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.florent37.glidepalette.GlidePalette;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<ColorItem> colorItems = new ArrayList<>();
    Palette.Swatch one, two, three, four, five, six, seven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap b = null;
        b = BitmapFactory.decodeResource(getResources(), R.drawable.img1);


        final Bitmap finalB = b;
        Palette.from(b)
                .generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch textSwatch = palette.getVibrantSwatch();
                        one = palette.getDarkMutedSwatch();
                        two = palette.getDarkVibrantSwatch();
                        three = palette.getDominantSwatch();
                        four = palette.getLightMutedSwatch();
                        five = palette.getLightVibrantSwatch();
                        six = palette.getVibrantSwatch();
                        seven = palette.getMutedSwatch();
                        Palette p = Palette.from(finalB).generate();

                        List<Palette.Swatch> pss = p.getSwatches();
                        int populationtemp = 0;
                        int colortemp = 0;
                        for (int j = 0; j < pss.size(); j++) {
                            Palette.Swatch ps = pss.get(j);
                            int color = ps.getRgb();
                            int population = ps.getPopulation();
                            float[] hsl = ps.getHsl();
                            int bodyTextColor = ps.getBodyTextColor();
                            int titleTextColor = ps.getTitleTextColor();
                           /* if (population > populationtemp){
                                populationtemp = population;
                                colortemp = color;
                            }*/
                            colorItems.add(new ColorItem(String.valueOf(color), String.valueOf(population), String.valueOf(bodyTextColor)));

                        }

//                        colorItems.add(new ColorItem(String.valueOf(colortemp), String.valueOf(populationtemp), String.valueOf(populationtemp)));



                        if (textSwatch == null) {
                            Toast.makeText(MainActivity.this, "Null swatch :(", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                });


        recyclerView = findViewById(R.id.color_rv);
        adapter = new MyAdapter(colorItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }
}