package com.animsh.respocetojsonfile.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.animsh.respocetojsonfile.R;
import com.animsh.respocetojsonfile.model.PokemonList;
import com.animsh.respocetojsonfile.network.ApiCalls;
import com.animsh.respocetojsonfile.network.NetworkUtils;
import com.animsh.respocetojsonfile.utils.JsonUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static String BASE_URL = "https://pokeapi.co/api/v2/";
    private String RESPONSE_TAG = "RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = NetworkUtils.getClient(BASE_URL);
        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        Call<PokemonList> listCall = apiCalls.getPokemonList(1118, 0);
        listCall.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.code() != 200) {
                    Log.e(RESPONSE_TAG, "onResponse: " + response.errorBody());
                    return;
                }

                PokemonList list = response.body();
                try {
                    JsonUtil.toJSon(list, MainActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e(RESPONSE_TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}