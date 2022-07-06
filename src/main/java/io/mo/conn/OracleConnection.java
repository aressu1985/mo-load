package io.mo.conn;

import io.mo.util.DBConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection implements DatabaseConnection {
    private static String host = DBConfigUtil.getOracleServerIP();
    private static String port = DBConfigUtil.getOraclePort();
    private static String SID = DBConfigUtil.getOracleSID();
    private static String username = DBConfigUtil.getOracleUsername();
    private static String password = DBConfigUtil.getOraclePassword();
    @Override
    public Connection BuildDatabaseConnection() throws SQLException {
        String ORACLE_CONNECTION;
        ORACLE_CONNECTION = "jdbc:oracle:thin:@" + host + ":" + port + "/" + SID;
        System.out.println(ORACLE_CONNECTION);
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(ORACLE_CONNECTION,username,password);
    }
}
