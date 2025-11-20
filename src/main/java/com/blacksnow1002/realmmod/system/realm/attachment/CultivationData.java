package com.blacksnow1002.realmmod.system.realm.attachment;

import com.blacksnow1002.realmmod.system.realm.data.CultivationRealm;
import com.blacksnow1002.realmmod.system.realm.data.RealmRegistry;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class CultivationData implements ValueIOSerializable {

    private String realmId;
    private int cultivation;
    private int requiredCultivation;
    private float successRate;

    public CultivationData() {
        this.realmId = RealmRegistry.RealmIds.NORMAL_PEOPLE;
        this.cultivation = 0;
        this.requiredCultivation = 10;
        this.successRate = 1.0f;
    }

    public CultivationData(String realmId, int cultivation, int requiredCultivation, float successRate) {
        this.realmId = realmId;
        this.cultivation = cultivation;
        this.requiredCultivation = requiredCultivation;
        this.successRate = successRate;
    }


    public String getRealmId() { return realmId; }

    public void setRealmId(String realmId) { this.realmId = realmId; }

    public int getCultivation() {
        return cultivation;
    }

    public void setCultivation(int amount) {
        cultivation = amount;
    }

    public void addCultivation(int amount) {
        cultivation += amount;
    }

    public int getRequiredCultivation() { return requiredCultivation; }

    public void setRequiredCultivation(int requiredCultivation) { this.requiredCultivation = requiredCultivation; }

    public float getSuccessRate() { return successRate; }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }

    @Override
    public void serialize(ValueOutput output) {
    }

    @Override
    public void deserialize(ValueInput input) {

    }
}
