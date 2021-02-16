package com.animsh.respocetojsonfile.models;

import java.io.Serializable;

/**
 * Created by animsh on 12/30/2020.
 */
public class PokemonType implements Serializable {
    private String slot;
    private Type type;

    public PokemonType(String slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
