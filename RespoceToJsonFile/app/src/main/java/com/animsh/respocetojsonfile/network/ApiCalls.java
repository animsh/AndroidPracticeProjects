package com.animsh.respocetojsonfile.network;

import com.animsh.respocetojsonfile.model.PokemonDetails;
import com.animsh.respocetojsonfile.model.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by animsh on 12/30/2020.
 */
public interface ApiCalls {

    @GET("pokemon")
    Call<PokemonList> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<PokemonDetails> getPokemonType(@Path("id") int id);

}
