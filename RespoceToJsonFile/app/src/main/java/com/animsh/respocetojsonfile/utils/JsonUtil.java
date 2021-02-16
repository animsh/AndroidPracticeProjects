package com.animsh.respocetojsonfile.utils;

import android.content.Context;

import com.animsh.respocetojsonfile.models.Pokemon;
import com.animsh.respocetojsonfile.models.PokemonDetails;
import com.animsh.respocetojsonfile.models.PokemonList;
import com.animsh.respocetojsonfile.models.PokemonType;
import com.animsh.respocetojsonfile.network.ApiCalls;
import com.animsh.respocetojsonfile.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by animsh on 12/30/2020.
 */
public class JsonUtil implements Serializable {
    private static final String JSON_TAG = "JSON_WRITE";
    private static final String FILE_NAME = "POKEFILE.json";


    public static String toJSon(PokemonList pokemonCollection, Context context) throws IOException {
        // Here we convert Java Object to JSON
        JSONObject jsonTypes = new JSONObject();
        String BASE_URL = "https://pokeapi.co/api/v2/";
        Retrofit retrofit = NetworkUtils.getClient(BASE_URL);
        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        PokemonList list = pokemonCollection;
        int count = Integer.parseInt(list.getCount());
        String[] idParser;
        List<Pokemon> pokemonList = list.getResults();
        for (int i = 0; i < count; i++) {
            JSONArray jsonArr = new JSONArray();
            idParser = pokemonList.get(i).getUrl().trim().split("/");
            Call<PokemonDetails> typeCall = apiCalls.getPokemonType(Integer.parseInt(idParser[idParser.length - 1]));
            String[] finalIdParser = idParser;
            typeCall.enqueue(new Callback<PokemonDetails>() {
                @Override
                public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                    PokemonDetails pokemonDetails = response.body();
                    for (PokemonType pokemonType : pokemonDetails.getTypes()) {
                        JSONObject jsonType = new JSONObject(); // we need another object to store the address
                        try {
                            JSONObject type = new JSONObject(); // we need another object to store the address
                            type.put("name", pokemonType.getType().getName());
                            type.put("url", pokemonType.getType().getUrl());
                            jsonType.put("slot", pokemonType.getSlot());
                            jsonType.put("type", type);
                            jsonArr.put(jsonType);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        jsonTypes.put(finalIdParser[finalIdParser.length - 1], jsonArr);
                        // Convert JsonObject to String Format
                        String userString = jsonTypes.toString();
                        // Define the File Path and its Name
                        File file = new File(context.getFilesDir(), FILE_NAME);
                        FileWriter fileWriter = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(userString);
                        bufferedWriter.close();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PokemonDetails> call, Throwable t) {
                }
            });
        }
        return jsonTypes.toString();
    }
}
