package com.blacksnow1002.realmmod.system.realm.data.types.qi_refining;

import com.blacksnow1002.realmmod.system.realm.data.BaseRealm;
import com.blacksnow1002.realmmod.system.realm.data.CultivationRealm;
import com.blacksnow1002.realmmod.system.realm.data.RealmRegistry;

public class QiRefining8 extends BaseRealm {

    public static final QiRefining8 INSTANCE = new QiRefining8();

    private QiRefining8() {
        super(
                RealmRegistry.RealmIds.QI_REFINING_8,
                "練氣八層",
                8,
                CultivationRealm.QI_REFINING,
                8,
                QiRefining9.INSTANCE
        );
    }

    @Override
    public int getRequiredCultivation() {
        return 100;
    }

    @Override
    public String[] getRequiredAssignment() {
        return new String[0];
    }

    @Override
    public float getBreakthroughSuccessRate() {
        return 1.0f;
    }


    @Override
    public float getCultivationSubtractRate() {
        return 0.1f;
    }

    @Override
    public float getBreakthroughSuccessAddRate() {
        return 0.03f;
    }


    @Override
    public int getBaseHp() {
        return 0;
    }

    @Override
    public int getBaseMp() {
        return 0;
    }

    @Override
    public int getBaseAttack() {
        return 0;
    }
}
