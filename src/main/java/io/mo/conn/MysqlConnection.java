package io.mo.conn;

import io.mo.util.DBConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection implements DatabaseConnection {

    private static String host = DBConfigUtil.getMysqlServerIP();
    private static String port = DBConfigUtil.getMysqlPort();
    private static String database = DBConfigUtil.getMysqlDatabase();
    private static String username = DBConfigUtil.getMysqlUsername();
    private static String password = DBConfigUtil.getMysqlPassword();
    private static String mysqldriver = DBConfigUtil.getMysqlDriver();

    public Connection BuildDatabaseConnection() throws SQLException {
        String MYSQL_CONNECTION;
        MYSQL_CONNECTION = "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password;
        //System.out.println(MYSQL_CONNECTION);
        try{
            Class.forName(mysqldriver).newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(MYSQL_CONNECTION);
    }
}
