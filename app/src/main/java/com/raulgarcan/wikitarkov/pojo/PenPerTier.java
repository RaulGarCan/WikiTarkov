package com.raulgarcan.wikitarkov.pojo;

import java.util.HashMap;

public class PenPerTier {
    private int tier1;
    private int tier2;
    private int tier3;
    private int tier4;
    private int tier5;
    private int tier6;

    public PenPerTier(int tier1, int tier2, int tier3, int tier4, int tier5, int tier6) {
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.tier4 = tier4;
        this.tier5 = tier5;
        this.tier6 = tier6;
    }
    public PenPerTier(HashMap<String, Object> map){
        this.tier1 = Integer.parseInt(map.get("tier1").toString());
        this.tier2 = Integer.parseInt(map.get("tier2").toString());
        this.tier3 = Integer.parseInt(map.get("tier3").toString());
        this.tier4 = Integer.parseInt(map.get("tier4").toString());
        this.tier5 = Integer.parseInt(map.get("tier5").toString());
        this.tier6 = Integer.parseInt(map.get("tier6").toString());
    }

    public int getTier1() {
        return tier1;
    }

    public void setTier1(int tier1) {
        this.tier1 = tier1;
    }

    public int getTier2() {
        return tier2;
    }

    public void setTier2(int tier2) {
        this.tier2 = tier2;
    }

    public int getTier3() {
        return tier3;
    }

    public void setTier3(int tier3) {
        this.tier3 = tier3;
    }

    public int getTier4() {
        return tier4;
    }

    public void setTier4(int tier4) {
        this.tier4 = tier4;
    }

    public int getTier5() {
        return tier5;
    }

    public void setTier5(int tier5) {
        this.tier5 = tier5;
    }

    public int getTier6() {
        return tier6;
    }

    public void setTier6(int tier6) {
        this.tier6 = tier6;
    }

    @Override
    public String toString() {
        return "PenPerTier{" +
                "tier1=" + tier1 +
                ", tier2=" + tier2 +
                ", tier3=" + tier3 +
                ", tier4=" + tier4 +
                ", tier5=" + tier5 +
                ", tier6=" + tier6 +
                '}';
    }
}

