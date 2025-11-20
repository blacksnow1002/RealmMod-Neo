package com.blacksnow1002.realmmod.database;

import com.blacksnow1002.realmmod.system.realm.attachment.RealmData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class PlayerDataService {

    private static final String SAVE_SQL =
            "INSERT INTO player_progress (uuid, realmId, cultivation, required_cultivation, success_rate) " +
                    "VALUES (?, ?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE " +
                    "realmId = VALUES(realmId), " +
                    "cultivation = VALUES(cultivation), " +
                    "required_cultivation = VALUES(required_cultivation), " +
                    "success_rate = VALUES(success_rate)";

    private static final String LOAD_SQL =
            "SELECT * FROM player_progress WHERE uuid = ?";

    public static void savePlayerProgress(UUID uuid, String realmId, int cultivation, int requiredCultivation, float success_rate) {
        try (Connection conn = ConnectionPoolManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_SQL)) {

            ps.setString(1, uuid.toString());
            ps.setString(2, realmId);
            ps.setInt(3, cultivation);
            ps.setInt(4, requiredCultivation);
            ps.setFloat(5, success_rate);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to save player data.");
            e.printStackTrace();
        }
    }

    public static Optional<RealmData> loadPlayerProgress(UUID playerUUID) {
        try (Connection conn = ConnectionPoolManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(LOAD_SQL)) {

            ps.setString(1, playerUUID.toString());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String realmId = rs.getString("realmId");
                int cultivation = rs.getInt("cultivation");
                int requiredCultivation = rs.getInt("required_cultivation");
                float success_rate = rs.getFloat("success_rate");

                return Optional.of(new RealmData(realmId, cultivation, requiredCultivation, success_rate));
            }
        } catch (SQLException e) {
            System.err.println("❌ MariaDB 載入錯誤：" + playerUUID);
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
