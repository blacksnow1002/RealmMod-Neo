package com.blacksnow1002.realmmod.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolManager {

    private static HikariDataSource dataSource;
    private static ConnectionPoolManager INSTANCE;

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/neoforge_data";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private ConnectionPoolManager() {

    }

    public static ConnectionPoolManager getInstance() {
        // 確保 INSTANCE 實例化，且永遠不為 null
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPoolManager();
        }

        // 【重要】這裡處理連線池的初始化/重新初始化
        // 檢查 dataSource 是否已存在但已關閉 (如前一個問題所建議)
        if (dataSource == null || dataSource.isClosed()) {
            try {
                initializePool();
            } catch (Exception e) {
                // 如果初始化失敗，這裡應該拋出一個 RuntimeException
                // 而不是返回 null，以讓呼叫者知道發生了嚴重錯誤。
                System.err.println("FATAL: Failed to initialize database connection pool!");
                e.printStackTrace();
                // 可以選擇拋出異常，或者讓它繼續，但這可能會在後續的 getConnection() 中失敗
                // 為了避免潛在的 NullPointerException, 建議讓它繼續並在 getConnection 處處理
            }
        }
        return INSTANCE;
    }

    private static void initializePool() {
        HikariConfig config = new HikariConfig();

        // 設定 JDBC 資訊
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);

        // 設定連線池屬性 (可以根據伺服器負載調整)
        config.setPoolName("NeoForge-MariaDB-Pool");
        config.setMaximumPoolSize(10); // 最大同時連線數
        config.setMinimumIdle(5);     // 保持的最小閒置連線數
        config.setConnectionTimeout(30000); // 連線逾時時間 (毫秒)

        // MariaDB/MySQL 專用的最佳化設定
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
        System.out.println("✅ Database Connection Pool initialized.");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
