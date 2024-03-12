package com.raulgarcan.wikitarkov.pojo;

import com.raulgarcan.wikitarkov.pojo.enums.Trader;

public class Quest {
    private String name;
    private Trader trader;
    private boolean isCompleted;
    private boolean isForKappa;
    private boolean isForLightkeeper;

    public Quest(String name, Trader trader, boolean isCompleted, boolean isForKappa, boolean isForLightkeeper) {
        this.name = name;
        this.trader = trader;
        this.isCompleted = isCompleted;
        this.isForKappa = isForKappa;
        this.isForLightkeeper = isForLightkeeper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isForKappa() {
        return isForKappa;
    }

    public void setForKappa(boolean forKappa) {
        isForKappa = forKappa;
    }

    public boolean isForLightkeeper() {
        return isForLightkeeper;
    }

    public void setForLightkeeper(boolean forLightkeeper) {
        isForLightkeeper = forLightkeeper;
    }
}
