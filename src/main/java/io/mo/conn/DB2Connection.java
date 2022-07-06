package io.mo.conn;

import io.mo.util.DBConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB2Connection implements DatabaseConnection {
    private static String host = DBConfigUtil.getDB2ServerIP();
    private static String port = DBConfigUtil.getDB2Port();
    private static String database = DBConfigUtil.getDB2Database();
    private static String username = DBConfigUtil.getDB2Username();
    private static String password = DBConfigUtil.getDB2Password();
    @Override
    public Connection BuildDatabaseConnection() throws SQLException {
        String DB2_CONNECTION;
        DB2_CONNECTION="jdbc:db2://" + host + ":" + port + "/" + database;
        System.out.println(DB2_CONNECTION);
        try{
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB2_CONNECTION,username,password);
    }
}
