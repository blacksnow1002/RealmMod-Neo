package com.blacksnow1002.realmmod.system.realm.data;

public abstract class BaseRealm {

    private final String id;
    private final String displayName;
    private final int rankValue;
    private final CultivationRealm majorRealm;
    private final int layer;
    private final BaseRealm nextRealm;

    public BaseRealm(String id, String displayName, int rankValue, CultivationRealm majorRealm, int layer, BaseRealm nextRealm) {
        this.id = id;
        this.displayName = displayName;
        this.rankValue = rankValue;
        this.majorRealm = majorRealm;
        this.layer = layer;
        this.nextRealm = nextRealm;
    }


    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public int getRankValue() { return rankValue; }
    public CultivationRealm getMajorRealm() { return majorRealm; }
    public int getLayer() { return layer; }
    public BaseRealm getNextRealm() { return nextRealm; }

    public abstract int getRequiredCultivation();
    public abstract String[] getRequiredAssignment();
    public abstract float getBreakthroughSuccessRate();

    public abstract float getCultivationSubtractRate();
    public abstract float getBreakthroughSuccessAddRate();

    public abstract int getBaseHp();
    public abstract int getBaseMp();
    public abstract int getBaseAttack();

}
