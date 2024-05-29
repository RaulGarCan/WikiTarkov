package com.raulgarcan.wikitarkov.data;

public class AmmoDef {
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
    public AmmoDef(){}

    public AmmoDef(String caliber, int damage, int penetrationPower, int armorDamage, float accuracyModifier, float recoilModifier, float lightBleedModifier, float heavyBleedModifier, float fragmentationChance, float initialSpeed, boolean tracer, String tracerColor, String ammoType, int projectileCount, PenPerTier penPerTier) {
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
    }
    public AmmoDef(Ammo ammo, PenPerTier penPerTier) {
        this.caliber = ammo.getCaliber();
        this.damage = ammo.getDamage();
        this.penetrationPower = ammo.getPenetrationPower();
        this.armorDamage = ammo.getArmorDamage();
        this.accuracyModifier = ammo.getAccuracyModifier();
        this.recoilModifier = ammo.getRecoilModifier();
        this.lightBleedModifier = ammo.getLightBleedModifier();
        this.heavyBleedModifier = ammo.getHeavyBleedModifier();
        this.fragmentationChance = ammo.getFragmentationChance();
        this.initialSpeed = ammo.getInitialSpeed();
        this.tracer = ammo.isTracer();
        this.tracerColor = ammo.getTracerColor();
        this.ammoType = ammo.getAmmoType();
        this.projectileCount = ammo.getProjectileCount();
        this.penPerTier = penPerTier;
    }

    public String getCaliber() {
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

