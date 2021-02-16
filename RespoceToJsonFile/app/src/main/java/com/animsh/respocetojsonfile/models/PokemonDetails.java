package com.animsh.respocetojsonfile.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by animsh on 12/30/2020.
 */
public class PokemonDetails implements Serializable {
    List<PokemonType> types;

    public PokemonDetails(List<PokemonType> types) {
        this.types = types;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }
}
