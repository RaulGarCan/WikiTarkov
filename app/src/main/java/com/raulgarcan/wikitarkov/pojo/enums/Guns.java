package com.raulgarcan.wikitarkov.pojo.enums;

public enum Guns {
    PISTOL("Pistols"),
    PWD("PWD"),
    RIFLE("Rifles"),
    SHOTGUN("Shotguns"),
    OTHER("Others"),
    ALL("All");
    final String displayName;
    Guns(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    public static Guns getGunByName(String gunName){
        switch (gunName.toLowerCase()){
            case "pistols":
                return Guns.PISTOL;
            case "pwd":
                return Guns.PWD;
            case "rifles":
                return Guns.RIFLE;
            case "shotguns":
                return Guns.SHOTGUN;
            case "others":
                return Guns.OTHER;
            case "all":
                return Guns.ALL;
            default:
                return null;
        }
    }
}
