package com.raulgarcan.wikitarkov.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Ammo implements Serializable {
    private String caliber;
    private int damage;
    private int penetrationPower;
    private int armorDamage;
    private float accuracyModifier;
    private float recoilModifier;
    private float lightBleedModifier;
    private float heavyBleedModifier;
    private float fragmentationChance;
    private float initialSpeed;
    private boolean tracer;
    private String tracerColor;
    private String ammoType;
    private int projectileCount;
    private PenPerTier penPerTier;
    private String longName;
    private String shortName;
    public Ammo(){}

    public Ammo(String caliber, int damage, int penetrationPower, int armorDamage, float accuracyModifier, float recoilModifier, float lightBleedModifier, float heavyBleedModifier, float fragmentationChance, float initialSpeed, boolean tracer, String tracerColor, String ammoType, int projectileCount, PenPerTier penPerTier, String longName, String shortName) {
        this.caliber = caliber;
        this.damage = damage;
        this.penetrationPower = penetrationPower;
        this.armorDamage = armorDamage;
        this.accuracyModifier = accuracyModifier;
        this.recoilModifier = recoilModifier;
        this.lightBleedModifier = lightBleedModifier;
        this.heavyBleedModifier = heavyBleedModifier;
        this.fragmentationChance = fragmentationChance;
        this.initialSpeed = initialSpeed;
        this.tracer = tracer;
        this.tracerColor = tracerColor;
        this.ammoType = ammoType;
        this.projectileCount = projectileCount;
        this.penPerTier = penPerTier;
        this.longName = longName;
        this.shortName = shortName;
    }

    public Ammo(Ammo source){
        this.caliber = source.getCaliber();
        this.damage = source.getDamage();
        this.penetrationPower = source.getPenetrationPower();
        this.armorDamage = source.getArmorDamage();
        this.accuracyModifier = source.getAccuracyModifier();
        this.recoilModifier = source.getRecoilModifier();
        this.lightBleedModifier = source.getLightBleedModifier();
        this.heavyBleedModifier = source.getHeavyBleedModifier();
        this.fragmentationChance = source.getFragmentationChance();
        this.initialSpeed = source.getInitialSpeed();
        this.tracer = source.isTracer();
        this.tracerColor = source.getTracerColor();
        this.ammoType = source.getAmmoType();
        this.projectileCount = source.getProjectileCount();
        this.penPerTier = source.getPenPerTier();
        this.longName = source.getLongName();
        this.shortName = source.getShortName();
    }
    public Ammo(HashMap<String, Object> map){
        this.caliber = map.get("caliber").toString();
        this.damage = Integer.parseInt(map.get("damage").toString());
        this.penetrationPower = Integer.parseInt(map.get("penetrationPower").toString());
        this.armorDamage = Integer.parseInt(map.get("armorDamage").toString());
        this.accuracyModifier = Float.parseFloat(map.get("accuracyModifier").toString());
        this.recoilModifier = Float.parseFloat(map.get("accuracyModifier").toString());
        this.lightBleedModifier = Float.parseFloat(map.get("accuracyModifier").toString());
        this.heavyBleedModifier = Float.parseFloat(map.get("accuracyModifier").toString());
        this.fragmentationChance = Float.parseFloat(map.get("accuracyModifier").toString());
        this.initialSpeed = Float.parseFloat(map.get("accuracyModifier").toString());
        this.tracer = Boolean.getBoolean(map.get("tracer").toString());
        this.tracerColor = map.get("tracerColor").toString();
        this.ammoType = map.get("ammoType").toString();
        this.projectileCount = Integer.parseInt(map.get("projectileCount").toString());
        this.penPerTier = new PenPerTier((HashMap<String, Object>) map.get("penPerTier"));
        this.longName = map.get("longName").toString();
        this.shortName = map.get("shortName").toString();
    }

    public String getCaliber() {
        return caliber;
    }
    public String getDisplayCaliber(){
        if(caliber.equalsIgnoreCase("Caliber1143x23ACP")){
            return "Caliber.45ACP";
        }
        if(caliber.equalsIgnoreCase("Caliber86x70")){
            return "Caliber.338Lapua";
        }
        if(caliber.equalsIgnoreCase("Caliber762x35")){
            return "Caliber.300Blackout";
        }
        if(caliber.equalsIgnoreCase("Caliber9x33R")){
            return "Caliber.357Magnum";
        }
        if(caliber.equalsIgnoreCase("Caliber366TKM")){
            return "Caliber.366TKM";
        }
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPenetrationPower() {
        return penetrationPower;
    }

    public void setPenetrationPower(int penetrationPower) {
        this.penetrationPower = penetrationPower;
    }

    public int getArmorDamage() {
        return armorDamage;
    }

    public void setArmorDamage(int armorDamage) {
        this.armorDamage = armorDamage;
    }

    public float getAccuracyModifier() {
        return accuracyModifier;
    }

    public void setAccuracyModifier(float accuracyModifier) {
        this.accuracyModifier = accuracyModifier;
    }

    public float getRecoilModifier() {
        return recoilModifier;
    }

    public void setRecoilModifier(float recoilModifier) {
        this.recoilModifier = recoilModifier;
    }

    public float getLightBleedModifier() {
        return lightBleedModifier;
    }

    public void setLightBleedModifier(float lightBleedModifier) {
        this.lightBleedModifier = lightBleedModifier;
    }

    public float getHeavyBleedModifier() {
        return heavyBleedModifier;
    }

    public void setHeavyBleedModifier(float heavyBleedModifier) {
        this.heavyBleedModifier = heavyBleedModifier;
    }

    public float getFragmentationChance() {
        return fragmentationChance;
    }

    public void setFragmentationChance(float fragmentationChance) {
        this.fragmentationChance = fragmentationChance;
    }

    public float getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(float initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public boolean isTracer() {
        return tracer;
    }

    public void setTracer(boolean tracer) {
        this.tracer = tracer;
    }

    public String getTracerColor() {
        return tracerColor;
    }

    public void setTracerColor(String tracerColor) {
        this.tracerColor = tracerColor;
    }

    public String getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(String ammoType) {
        this.ammoType = ammoType;
    }

    public int getProjectileCount() {
        return projectileCount;
    }

    public void setProjectileCount(int projectileCount) {
        this.projectileCount = projectileCount;
    }

    public PenPerTier getPenPerTier() {
        return penPerTier;
    }

    public void setPenPerTier(PenPerTier penPerTier) {
        this.penPerTier = penPerTier;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public String toString() {
        return "AmmoDef{" +
                "caliber='" + caliber + '\'' +
                ", damage=" + damage +
                ", penetrationPower=" + penetrationPower +
                ", armorDamage=" + armorDamage +
                ", accuracyModifier=" + accuracyModifier +
                ", recoilModifier=" + recoilModifier +
                ", lightBleedModifier=" + lightBleedModifier +
                ", heavyBleedModifier=" + heavyBleedModifier +
                ", fragmentationChance=" + fragmentationChance +
                ", initialSpeed=" + initialSpeed +
                ", tracer=" + tracer +
                ", tracerColor='" + tracerColor + '\'' +
                ", ammoType='" + ammoType + '\'' +
                ", projectileCount=" + projectileCount +
                ", penPerTier=" + penPerTier +
                ", longName='" + longName + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
