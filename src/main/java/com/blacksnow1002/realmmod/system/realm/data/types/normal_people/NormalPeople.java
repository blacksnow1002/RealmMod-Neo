package com.blacksnow1002.realmmod.system.realm.data.types.normal_people;

import com.blacksnow1002.realmmod.system.realm.data.BaseRealm;
import com.blacksnow1002.realmmod.system.realm.data.CultivationRealm;
import com.blacksnow1002.realmmod.system.realm.data.RealmRegistry;
import com.blacksnow1002.realmmod.system.realm.data.types.qi_refining.QiRefining1;

public class NormalPeople extends BaseRealm {

    public static final NormalPeople INSTANCE = new NormalPeople();

    private NormalPeople() {
        super(
                RealmRegistry.RealmIds.NORMAL_PEOPLE,
                "凡夫俗子",
                0,
                CultivationRealm.NORMAL_PEOPLE,
                1,
                QiRefining1.INSTANCE

        );
    }

    @Override
    public int getRequiredCultivation() {
        return 10;
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
