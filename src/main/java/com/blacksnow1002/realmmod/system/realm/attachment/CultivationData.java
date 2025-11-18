package com.blacksnow1002.realmmod.system.realm.attachment;

import com.blacksnow1002.realmmod.system.realm.data.CultivationRealm;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class CultivationData implements ValueIOSerializable {

    private CultivationRealm realm;
    private int layer;
    private int cultivation;
    private float successRate;

    public CultivationData() {
        this.realm = CultivationRealm.NORMAL_PEOPLE;
        this.layer = 1;
        this.cultivation = 0;
        this.successRate = 1.0f;
    }

    public CultivationData(String realm, int layer, int cultivation, float successRate) {
        this.realm = CultivationRealm.valueOf(realm);
        this.layer = layer;
        this.cultivation = cultivation;
        this.successRate = successRate;
    }

    public CultivationRealm getCultivationRealm() {
        return realm;
    }

    public void setCultivationRealm(CultivationRealm cultivationRealm) {
        realm = cultivationRealm;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getCultivation() {
        return cultivation;
    }

    public void setCultivation(int amount) {
        cultivation = amount;
    }

    public void addCultivation(int amount) {
        cultivation += amount;
    }

    public float getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }

    @Override
    public void serialize(ValueOutput output) {
        output.putString("Realm", realm.name());
        output.putInt("Layer", layer);
        output.putInt("Cultivation", cultivation);
        output.putFloat("SuccessRate", successRate);
    }

    @Override
    public void deserialize(ValueInput input) {
        input.getString("Realm").ifPresent(realm -> this.realm = CultivationRealm.valueOf(realm));
        input.getInt("Layer").ifPresent(layer -> this.layer = layer);
        input.getInt("Cultivation").ifPresent(cultivation -> this.cultivation = cultivation);
        successRate = input.getFloatOr("SuccessfulRate", 1.0f);
    }
}
