package com.animsh.colorpalette;

public class ColorItem {
    private String color;
    private String population;
    private String bodyTextColor;

    public ColorItem() {
    }

    public ColorItem(String color, String population, String bodyTextColor) {
        this.color = color;
        this.population = population;
        this.bodyTextColor = bodyTextColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getBodyTextColor() {
        return bodyTextColor;
    }

    public void setBodyTextColor(String bodyTextColor) {
        this.bodyTextColor = bodyTextColor;
    }
}
