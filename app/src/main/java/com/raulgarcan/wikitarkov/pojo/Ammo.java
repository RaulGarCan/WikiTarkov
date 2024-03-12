package com.raulgarcan.wikitarkov.pojo;

public class Ammo {
    private String caliber;
    private String longName;
    private String shortName;
    private double dmg;
    private double pen;
    private int[] penPerTier;

    public Ammo(String caliber, String longName, String shortName, double dmg, double pen, int[] penPerTier) {
        this.caliber = caliber;
        this.longName = longName;
        this.shortName = shortName;
        this.dmg = dmg;
        this.pen = pen;
        this.penPerTier = penPerTier;
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

    public void setDmg(float dmg) {
        this.dmg = dmg;
    }

    public double getPen() {
        return pen;
    }

    public void setPen(float pen) {
        this.pen = pen;
    }

    public int[] getPenPerTier() {
        return penPerTier;
    }

    public void setPenPerTier(int[] penPerTier) {
        this.penPerTier = penPerTier;
    }
}
