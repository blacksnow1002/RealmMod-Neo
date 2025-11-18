package com.blacksnow1002.realmmod.system.realm.data;

public class RealmStats {

    private final int requiredCultivation;
    private final float breakthroughChance;
    private final int healthBonus;
    private final int attackBonus;

    public RealmStats(int requiredCultivation,
                      float breakthroughChance,
                      int healthBonus,
                      int attackBonus) {
        this.requiredCultivation = requiredCultivation;
        this.breakthroughChance = breakthroughChance;
        this.healthBonus = healthBonus;
        this.attackBonus = attackBonus;
    }

    public int getRequiredCultivation() {
        return requiredCultivation;
    }

    public float getBreakthroughChance() {
        return breakthroughChance;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }
}
