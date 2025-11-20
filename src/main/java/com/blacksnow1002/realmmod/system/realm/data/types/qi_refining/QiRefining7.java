package com.blacksnow1002.realmmod.system.realm.data.types.qi_refining;

import com.blacksnow1002.realmmod.system.realm.data.BaseRealm;
import com.blacksnow1002.realmmod.system.realm.data.CultivationRealm;
import com.blacksnow1002.realmmod.system.realm.data.RealmRegistry;

public class QiRefining7 extends BaseRealm {

    public static final QiRefining7 INSTANCE = new QiRefining7();

    private QiRefining7() {
        super(
                RealmRegistry.RealmIds.QI_REFINING_7,
                "練氣七層",
                7,
                CultivationRealm.QI_REFINING,
                7,
                QiRefining8.INSTANCE
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
