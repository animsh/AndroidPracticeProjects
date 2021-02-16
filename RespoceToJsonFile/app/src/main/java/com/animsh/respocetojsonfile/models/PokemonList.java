package com.animsh.respocetojsonfile.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by animsh on 12/30/2020.
 */
public class PokemonList implements Serializable {
    private String count;
    private List<Pokemon> results;

    public PokemonList(String count, List<Pokemon> results) {
        this.count = count;
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}
