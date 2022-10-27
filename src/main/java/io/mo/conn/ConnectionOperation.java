package io.mo.conn;

import io.mo.util.DBConfigUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionOperation {

    public static Connection getConnection() throws Exception {
        DatabaseConnection databaseConnection = new MOConnection();
       /* if (DBConfigUtil.getType().equals("mysql")){
            databaseConnection = new MysqlConnection();
        }else if(DBConfigUtil.getType().equals("db2")){
            databaseConnection = new DB2Connection();
        }else if(DBConfigUtil.getType().equals("mo")){
            databaseConnection = new MOConnection();
        }else if(DBConfigUtil.getType().equals("oracle")){
            databaseConnection = new OracleConnection();
        }
        else{
            throw new Exception("Don't support [" + DBConfigUtil.getType() + "] database!" );
        }*/
        //System.out.println("数据库连接成功!");
        return databaseConnection.BuildDatabaseConnection();
    }

    public static void CloseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
