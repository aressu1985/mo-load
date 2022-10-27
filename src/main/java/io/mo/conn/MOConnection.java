package io.mo.conn;

import io.mo.util.DBConfigUtil;
import io.mo.util.MoConfUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MOConnection implements DatabaseConnection {
    private static String conn = MoConfUtil.getURL();
    private static String database = MoConfUtil.getDefaultDatabase();
    private static String username = MoConfUtil.getUserName();
    private static String password = MoConfUtil.getUserpwd();
    private static String driver = MoConfUtil.getDriver();

    @Override
    public Connection BuildDatabaseConnection() throws SQLException {

        try{
            Class.forName(driver).newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(conn,username,password);

    }
}
