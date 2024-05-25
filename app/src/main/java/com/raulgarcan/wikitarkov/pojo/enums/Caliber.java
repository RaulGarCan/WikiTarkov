package com.raulgarcan.wikitarkov.pojo.enums;

public enum Caliber {
    MM762X25("762x25mm Tokarev",Guns.PISTOL,"762x25"),
    MM9X18("9x18mm Makarov",Guns.PISTOL,"9x18"),
    MM9X19("9x19mm Parabellum",Guns.PISTOL,"9x19"),
    MM9X21("9x21mm Gyurza",Guns.PISTOL,"9x21"),
    MAGNUM357(".357 Magnum",Guns.PISTOL,".357Magnum"),
    ACP45(".45 ACP",Guns.PISTOL,".45ACP"),
    MM46X30("46x30mm HK",Guns.PWD,"46x30"),
    MM57X28("57x28mm FN",Guns.PWD,"57x28"),
    MM545X39("545x39mm",Guns.RIFLE,"545x39"),
    MM556X45("556x45mm NATO",Guns.RIFLE,"556x45"),
    MM68X51("68x51mm",Guns.RIFLE,"68x51"),
    BLACKOUT300(".300 Blackout",Guns.RIFLE,".300Blackout"),
    MM762X39("762x39mm",Guns.RIFLE,"762x39"),
    MM762X51("762x51mm NATO",Guns.RIFLE,"762x51"),
    MM762X54("762x54mmR",Guns.RIFLE,"762x54"),
    LAPUA338(".338 Lapua Magnum",Guns.RIFLE,".338Lapua"),
    MM9X39("9x39mm",Guns.RIFLE,"9x39"),
    TKM366(".366 TKM",Guns.RIFLE,".366TKM"),
    MM127X55("12.7x55mm STs-130",Guns.RIFLE,"127x55"),
    SLUG1270("12/70",Guns.SHOTGUN,"12/70"),
    SLUG2070("20/70",Guns.SHOTGUN,"20/70"),
    MM2375("23x75mm",Guns.SHOTGUN,"23x75"),
    MM40X46("40x46mm",Guns.OTHER,"40x46");
    final String displayName;
    final Guns gunType;
    final String dbName;
    Caliber(String displayName, Guns gunType, String dbName){
        this.displayName = displayName;
        this.gunType = gunType;
        this.dbName = dbName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Guns getGunType() {
        return gunType;
    }

    public String getDbName() {
        return dbName;
    }

    public static Caliber getCaliberByName(String caliberName){
        switch (caliberName){
            case "762x25mm Tokarev":
                return Caliber.MM762X25;
            case "9x18mm Makarov":
                return Caliber.MM9X18;
            case "9x19mm Parabellum":
                return Caliber.MM9X19;
            case "9x21mm Gyurza":
                return Caliber.MM9X21;
            case ".357 Magnum":
                return Caliber.MAGNUM357;
            case ".45 ACP":
                return Caliber.ACP45;
            case "46x30mm HK":
                return Caliber.MM46X30;
            case "57x28mm FN":
                return Caliber.MM57X28;
            case "545x39mm":
                return Caliber.MM545X39;
            case "556x45mm NATO":
                return Caliber.MM556X45;
            case "68x51mm":
                return Caliber.MM68X51;
            case ".300 Blackout":
                return Caliber.BLACKOUT300;
            case "762x39mm":
                return Caliber.MM762X39;
            case "762x51mm NATO":
                return Caliber.MM762X51;
            case "762x54mmR":
                return Caliber.MM762X54;
            case ".338 Lapua Magnum":
                return Caliber.LAPUA338;
            case "9x39mm":
                return Caliber.MM9X39;
            case ".366 TKM":
                return Caliber.TKM366;
            case "12.7x55mm STs-130":
                return Caliber.MM127X55;
            case "12/70":
                return Caliber.SLUG1270;
            case "20/70":
                return Caliber.SLUG2070;
            case "23x75mm":
                return Caliber.MM2375;
            case "40x46mm":
                return Caliber.MM40X46;
            default:
                return null;
        }
    }
}
