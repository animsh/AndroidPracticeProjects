package com.animsh.respocetojsonfile.models;

import java.io.Serializable;

/**
 * Created by animsh on 12/30/2020.
 */
public class Type implements Serializable {
    private String name;
    private String url;

    public Type(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
