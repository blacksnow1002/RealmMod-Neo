package com.blacksnow1002.realmmod.system.realm.data;

import com.blacksnow1002.realmmod.system.realm.data.types.normal_people.NormalPeople;
import com.blacksnow1002.realmmod.system.realm.data.types.qi_refining.*;

import java.util.HashMap;
import java.util.Map;

public class RealmRegistry {
    private static final Map<String, BaseRealm> REALMS = new HashMap<>();

    static {
        register(NormalPeople.INSTANCE);

        register(QiRefining1.INSTANCE);
        register(QiRefining2.INSTANCE);
        register(QiRefining3.INSTANCE);
        register(QiRefining4.INSTANCE);
        register(QiRefining5.INSTANCE);
        register(QiRefining6.INSTANCE);
        register(QiRefining7.INSTANCE);
        register(QiRefining8.INSTANCE);
        register(QiRefining9.INSTANCE);
        register(QiRefiningPerfect.INSTANCE);
    }

    private static void register(BaseRealm realm) {
        if (REALMS.containsKey(realm.getId())) {
            throw new IllegalStateException("Duplicate realm id " + realm.getId());
        }
        REALMS.put(realm.getId(), realm);
    }

    public static final class RealmIds {
        public static final String NORMAL_PEOPLE = "normal_people";

        public static final String QI_REFINING_1 = "qi_refining_1";
        public static final String QI_REFINING_2 = "qi_refining_2";
        public static final String QI_REFINING_3 = "qi_refining_3";
        public static final String QI_REFINING_4 = "qi_refining_4";
        public static final String QI_REFINING_5 = "qi_refining_5";
        public static final String QI_REFINING_6 = "qi_refining_6";
        public static final String QI_REFINING_7 = "qi_refining_7";
        public static final String QI_REFINING_8 = "qi_refining_8";
        public static final String QI_REFINING_9 = "qi_refining_9";
        public static final String QI_REFINING_PERFECT = "qi_refining_perfect";
    }

    public static BaseRealm getRealmById(String id) {
        BaseRealm realm = REALMS.get(id);
        if (realm == null) {
            System.err.println("No realm found with id " + id);
            return null;
        }
        return realm;
    }
}
