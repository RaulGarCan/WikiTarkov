package com.raulgarcan.wikitarkov.pojo.enums;

public enum MapTarkov {
    FACTORY("","Factory"),
    WOODS("woods.jpg","Woods"),
    CUSTOMS("customs.jpg","Customs"),
    INTERCHANGE("interchange.jpg","Interchange"),
    RESERVE("reserve.jpg","Reserve"),
    SHORELINE("","Shoreline"),
    THELAB("","The Lab"),
    LIGHTHOUSE("","Lighthouse"),
    STREETS("streets.jpg","Streets"),
    GROUNDZERO("groundzero.jpg","Ground Zero");
    final String fileName;
    final String displayName;
    MapTarkov(String fileName, String displayName){
        this.fileName = fileName;
        this.displayName = displayName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
