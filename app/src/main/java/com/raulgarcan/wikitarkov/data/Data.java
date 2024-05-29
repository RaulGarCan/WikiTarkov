package com.raulgarcan.wikitarkov.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private ArrayList<Ammo> ammo;

    public Data() {
        this.ammo = new ArrayList<>();
    }

    public ArrayList<Ammo> getAmmo() {
        return ammo;
    }

    public void setAmmo(ArrayList<Ammo> ammo) {
        this.ammo = ammo;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ammo=" + ammo +
                '}';
    }
}
