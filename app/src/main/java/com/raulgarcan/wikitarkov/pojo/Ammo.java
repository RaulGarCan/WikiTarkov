package com.raulgarcan.wikitarkov.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Ammo implements Serializable {
    private int aaId;
    private String caliber;
    private String longName;
    private String shortName;
    private double dmg;
    private double pen;
    private double armorDmg;
    private double accuracy;
    private double recoil;
    private double fragChance;
    private double lightBleedPercent;
    private double heavyBleedPercent;
    private double speed;
    private ArrayList<Long> penPerTier;

    public Ammo(int aaId, String caliber, String longName, String shortName, double dmg, double pen, double armorDmg, double accuracy, double recoil, double fragChance, double lightBleedPercent, double heavyBleedPercent, double speed) {
        this.aaId = aaId;
        this.caliber = caliber;
        this.longName = longName;
        this.shortName = shortName;
        this.dmg = dmg;
        this.pen = pen;
        this.armorDmg = armorDmg;
        this.accuracy = accuracy;
        this.recoil = recoil;
        this.fragChance = fragChance;
        this.lightBleedPercent = lightBleedPercent;
        this.heavyBleedPercent = heavyBleedPercent;
        this.speed = speed;
        this.penPerTier = new ArrayList<>();
    }
    public Ammo(Ammo source) {
        this.aaId = source.aaId;
        this.caliber = source.caliber;
        this.longName = source.longName;
        this.shortName = source.shortName;
        this.dmg = source.dmg;
        this.pen = source.pen;
        this.armorDmg = source.armorDmg;
        this.accuracy = source.accuracy;
        this.recoil = source.recoil;
        this.fragChance = source.fragChance;
        this.lightBleedPercent = source.lightBleedPercent;
        this.heavyBleedPercent = source.heavyBleedPercent;
        this.speed = source.speed;
        this.penPerTier = source.getPenPerTier();
    }
    public Ammo(HashMap<String, Object> mapa){
        this.aaId = Integer.parseInt(mapa.get("aaId").toString());
        this.caliber = mapa.get("caliber").toString();
        this.longName = mapa.get("longName").toString();
        this.shortName = mapa.get("shortName").toString();
        this.dmg = Double.valueOf(mapa.get("dmg").toString());
        this.pen = Double.valueOf(mapa.get("pen").toString());
        this.armorDmg = Double.valueOf(mapa.get("armorDmg").toString());
        this.accuracy = Double.valueOf(mapa.get("accuracy").toString());
        this.recoil = Double.valueOf(mapa.get("recoil").toString());
        this.fragChance = Double.valueOf(mapa.get("fragChance").toString());
        this.lightBleedPercent = Double.valueOf(mapa.get("lightBleedPercent").toString());
        this.heavyBleedPercent = Double.valueOf(mapa.get("heavyBleedPercent").toString());
        this.speed = Double.valueOf(mapa.get("speed").toString());
        this.penPerTier = (ArrayList<Long>) mapa.get("penPerTier");
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public double getPen() {
        return pen;
    }

    public void setPen(double pen) {
        this.pen = pen;
    }

    public double getArmorDmg() {
        return armorDmg;
    }

    public void setArmorDmg(double armorDmg) {
        this.armorDmg = armorDmg;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getRecoil() {
        return recoil;
    }

    public void setRecoil(double recoil) {
        this.recoil = recoil;
    }

    public double getFragChance() {
        return fragChance;
    }

    public void setFragChance(double fragChance) {
        this.fragChance = fragChance;
    }

    public double getLightBleedPercent() {
        return lightBleedPercent;
    }

    public void setLightBleedPercent(double lightBleedPercent) {
        this.lightBleedPercent = lightBleedPercent;
    }

    public double getHeavyBleedPercent() {
        return heavyBleedPercent;
    }

    public void setHeavyBleedPercent(double heavyBleedPercent) {
        this.heavyBleedPercent = heavyBleedPercent;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public ArrayList<Long> getPenPerTier() {
        ArrayList<Long> tmp = new ArrayList<>();
        for(long l : penPerTier){
            tmp.add(l);
        }
        return tmp;
    }

    public void setPenPerTier(long[] penPerTier) {
        for(long l : penPerTier){
            this.penPerTier.add(l);
        }
    }

    public int getAaId() {
        return aaId;
    }

    public void setAaId(int aaId) {
        this.aaId = aaId;
    }

    @Override
    public String toString() {
        return "Ammo{" +
                "caliber='" + caliber + '\'' +
                ", longName='" + longName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", dmg=" + dmg +
                ", pen=" + pen +
                ", armorDmg=" + armorDmg +
                ", accuracy=" + accuracy +
                ", recoil=" + recoil +
                ", fragChance=" + fragChance +
                ", lightBleedPercent=" + lightBleedPercent +
                ", heavyBleedPercent=" + heavyBleedPercent +
                ", speed=" + speed +
                ", penPerTier=" + penPerTier +
                '}';
    }
}
