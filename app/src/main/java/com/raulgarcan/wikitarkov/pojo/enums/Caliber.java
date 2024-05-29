package com.raulgarcan.wikitarkov.pojo.enums;

public enum Caliber {
    MM762X25("762x25TT", Guns.PISTOL,"mm762x25"),
    MM9X18("9x18PM", Guns.PISTOL,"mm9x18"),
    MM9X19("9x19PARA", Guns.PISTOL,"mm9x19"),
    MM9X21("9x21", Guns.PISTOL,"mm9x21"),
    MAGNUM357("9x33R", Guns.PISTOL,"magnum357"),
    ACP45("1143x23ACP", Guns.PISTOL,"acp45"),
    MM46X30("46x30", Guns.PWD,"mm46x30"),
    MM57X28("57x28", Guns.PWD,"mm57x28"),
    MM545X39("545x39", Guns.RIFLE,"mm545x39"),
    MM556X45("556x45NATO", Guns.RIFLE,"mm556x45"),
    MM68X51("68x51", Guns.RIFLE,"mm68x51"),
    BLACKOUT300("762x35", Guns.RIFLE,"blackout300"),
    MM762X39("762x39", Guns.RIFLE,"mm762x39"),
    MM762X51("762x51", Guns.RIFLE,"mm762x51"),
    MM762X54("762x54R", Guns.RIFLE,"mm762x54"),
    LAPUA338("86x70", Guns.RIFLE,"lapua338"),
    MM9X39("9x39", Guns.RIFLE,"mm9x39"),
    TKM366("366TKM", Guns.RIFLE,"tkm366"),
    MM127X55("127x55", Guns.RIFLE,"mm127x55"),
    SLUG1270("12g", Guns.SHOTGUN,"g12"),
    SLUG2070("20g", Guns.SHOTGUN,"g20"),
    MM2375("23x75", Guns.SHOTGUN,"mm23x75"),
    MM40X46("40x46", Guns.OTHER,"mm40x46");
    final String displayName;
    final Guns gunType;
    final String fileName;
    Caliber(String displayName, Guns gunType, String fileName){
        this.displayName = displayName;
        this.gunType = gunType;
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Guns getGunType() {
        return gunType;
    }

    public String getFileName() {
        return fileName;
    }

    public static Caliber getCaliberByName(String caliberName){
        switch (caliberName){
            case "762x25TT":
                return Caliber.MM762X25;
            case "9x18PM":
                return Caliber.MM9X18;
            case "9x19PARA":
                return Caliber.MM9X19;
            case "9x21":
                return Caliber.MM9X21;
            case "9x33R":
                return Caliber.MAGNUM357;
            case "1143x23ACP":
                return Caliber.ACP45;
            case "46x30":
                return Caliber.MM46X30;
            case "57x28":
                return Caliber.MM57X28;
            case "545x39":
                return Caliber.MM545X39;
            case "556x45NATO":
                return Caliber.MM556X45;
            case "68x51":
                return Caliber.MM68X51;
            case "762x35":
                return Caliber.BLACKOUT300;
            case "762x39":
                return Caliber.MM762X39;
            case "762x51":
                return Caliber.MM762X51;
            case "762x54R":
                return Caliber.MM762X54;
            case "86x70":
                return Caliber.LAPUA338;
            case "9x39":
                return Caliber.MM9X39;
            case "366TKM":
                return Caliber.TKM366;
            case "127x55":
                return Caliber.MM127X55;
            case "12g":
                return Caliber.SLUG1270;
            case "20g":
                return Caliber.SLUG2070;
            case "23x75":
                return Caliber.MM2375;
            case "40x46":
                return Caliber.MM40X46;
            default:
                return null;
        }
    }
}
