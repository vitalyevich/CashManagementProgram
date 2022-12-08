package com.cashmanagement.vitalyevich.client.model;

public abstract class ColorTable {

    public String colorFirst;

    public String colorSecond;

    public String getColorSecond() {
        return colorSecond;
    }

    public void setColorSecond(String colorSecond) {
        this.colorSecond = colorSecond;
    }

    public String getColorFirst() {
        return colorFirst;
    }

    public void setColorFirst(String colorFirst) {
        this.colorFirst = colorFirst;
    }
}
