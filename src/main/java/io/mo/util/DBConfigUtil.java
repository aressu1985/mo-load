package io.mo.util;

import java.io.FileNotFoundException;
import java.util.Map;

public class DBConfigUtil {
    private static YamlUtil conf = new YamlUtil();
    private static Map param = null;

    static {
        try {
            param = conf.getInfo("db.yml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
    common
     */
    public static String getType(){
        return (String) param.get("type");
    }

    public static String getThreadNum(){
        return (String) param.get("threadnum");
    }

    public static String getDuration(){
        return (String) param.get("duration");
    }

    public static String getCacheSize(){
        return (String) param.get("cachesize");
    }


    /*
    mysql
     */
    public static String getMysqlServerIP(){
        return (String) param.get("mysql-server");
    }

    public static String getMysqlDatabase(){
        return (String) param.get("mysql-database");
    }

    public static String getMysqlUsername(){
        return (String) param.get("mysql-username");
    }

    public static String getMysqlPassword(){
        return (String) param.get("mysql-password");
    }

    public static String getMysqlPort(){
        return (String) param.get("mysql-port");
    }

    public static String getMysqlDriver(){
        return (String) param.get("mysql-driver");
    }

    /*
    db2
    */
    public static String getDB2ServerIP() {return (String) param.get("db2-server");}
    public static String getDB2Database() {return (String) param.get("db2-database");}
    public static String getDB2Username() {return (String) param.get("db2-username");}
    public static String getDB2Password() {return (String) param.get("db2-password");}
    public static String getDB2Port() {return (String) param.get("db2-port");}

    /*
    kundb
     */
    public static String getMOUsername() {return (String) param.get("mo-username");}
    public static String getMOPassword() {return (String) param.get("mo-password");}
    public static String getMODatabase() {return (String) param.get("mo-database");}
    public static String getMODriver() {return (String) param.get("mo-driver");}
    public static String getMOConn() {return (String) param.get("mo-conn");}

    /*
    oracle
     */
    public static String getOracleServerIP() {return (String) param.get("oracle-host");}
    public static String getOracleUsername() {return (String) param.get("oracle-username");}
    public static String getOraclePassword() {return (String) param.get("oracle-password");}
    public static String getOraclePort() {return (String) param.get("oracle-port");}
    public static String getOracleSID() {return (String) param.get("oracle-sid");}

}
