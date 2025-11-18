package com.blacksnow1002.realmmod.system.realm.data;

public enum CultivationRealm {
    NORMAL_PEOPLE("凡夫俗子", 1),
    QI_REFINING("煉氣", 10),
    FOUNDATION("築基", 10),
    GOLDEN_CORE("金丹", 10),
    NASCENT_SOUL("元嬰", 10),
    SPIRIT_TRANSFORM("化神", 10),
    VOID_REFINING("煉虛", 10),
    INTEGRATION("合體", 10),
    GREAT_VENERABLE("大乘", 10),
    TRIBULATION("渡劫", 10);

    private final String displayName;
    private final int layerAmount;

    CultivationRealm(String displayName, int layerAmount) {
        this.displayName = displayName;
        this.layerAmount = layerAmount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLayerAmount() {
        return layerAmount;
    }
}
