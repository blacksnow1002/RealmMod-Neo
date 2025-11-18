package com.blacksnow1002.realmmod.database;

import com.blacksnow1002.realmmod.system.realm.attachment.CultivationData;
import com.blacksnow1002.realmmod.system.realm.data.CultivationRealm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class PlayerDataService {

    private static final String SAVE_SQL =
            "INSERT INTO player_progress (uuid, realm, layer, cultivation, success_rate) " +
                    "VALUES (?, ?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE " +
                    "realm = VALUES(realm), " +
                    "layer = VALUES(layer), " +
                    "cultivation = VALUES(cultivation), " +
                    "success_rate = VALUES(success_rate)";

    private static final String LOAD_SQL =
            "SELECT * FROM player_progress WHERE uuid = ?";

    public static void savePlayerProgress(UUID uuid, String realm, int layer, int cultivation, float success_rate) {
        try (Connection conn = ConnectionPoolManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_SQL)) {

            ps.setString(1, uuid.toString());
            ps.setString(2, realm);
            ps.setInt(3, layer);
            ps.setInt(4, cultivation);
            ps.setFloat(5, success_rate);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to save player data.");
            e.printStackTrace();
        }
    }

    public static Optional<CultivationData> loadPlayerProgress(UUID playerUUID) {
        try (Connection conn = ConnectionPoolManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(LOAD_SQL)) {

            ps.setString(1, playerUUID.toString());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String realm = rs.getString("realm");
                int layer = rs.getInt("layer");
                int cultivation = rs.getInt("cultivation");
                float success_rate = rs.getFloat("success_rate");

                return Optional.of(new CultivationData(realm, layer, cultivation, success_rate));
            }
        } catch (SQLException e) {
            System.err.println("❌ MariaDB 載入錯誤：" + playerUUID);
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
