package io.mo.conn;

import io.mo.util.DBConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MOConnection implements DatabaseConnection {
    private static String conn = DBConfigUtil.getMOConn();
    private static String database = DBConfigUtil.getMODatabase();
    private static String username = DBConfigUtil.getMOUsername();
    private static String password = DBConfigUtil.getMOPassword();
    private static String kundbdriver = DBConfigUtil.getMODriver();

    @Override
    public Connection BuildDatabaseConnection() throws SQLException {
        String KUNDB_CONNECTION;
        String KUNDB_USERNAME = username;
        String KUNDB_PASSWORD = password;
        //KUNDB_CONNECTION="jdbc:mysql://" + host+ "/" + database + "?user=" + username + "&password=" + password;
        KUNDB_CONNECTION = conn;

        try{
            Class.forName(kundbdriver).newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(KUNDB_CONNECTION,KUNDB_USERNAME,KUNDB_PASSWORD);

    }

    public static void main(String[] args){

         String[] label = null;
         String[][] values = null;

        MOConnection kunDBConnection = new MOConnection();
        try {
            Connection con = kunDBConnection.BuildDatabaseConnection();

            Statement statement = con.createStatement();
            String sql1 = "select JNSJ,TYXYM,GJ_ID,JNND,ZJLX,ZJHM,JNLX,JNDWMC,YW_UPDATE_TIME,JHPT_UPDATE_FLAG,JNFL,JHPT_UPDATE_TIME,JFJS,TASKID,JHPT_DELETE,XM,JNJE  from dwd_zwy_zgjfqk_iwi where zjhm ='132222200410256118';";
            sql1 = "insert into test values(0);"+
                    "insert into test values(1);";
            //String sql2 = "select JNSJ,TYXYM,GJ_ID,JNND,ZJLX,ZJHM,JNLX,JNDWMC,YW_UPDATE_TIME,JHPT_UPDATE_FLAG,JNFL,JHPT_UPDATE_TIME,JFJS,TASKID,JHPT_DELETE,XM,JNJE  from dwd_zwy_zgjfqk_iwi where zjhm ='132222200410256118';";
            //String sql2 = "insert into test values(1);";
            statement.addBatch(sql1);
            //statement.addBatch(sql2);
            int r[] = statement.executeBatch();
            /*while(resultSet.next()){
                ResultSetMetaData md = null;
                try {
                    md = resultSet.getMetaData();
                    int cols = md.getColumnCount();
                    label = new String[cols];
                    values = new String[10][cols];
                    for(int i = 0; i < cols; ++i) {
                        label[i] = md.getColumnLabel(i + 1);
                    }

                    int i = 0;
                    while(resultSet.next()) {
                        for(int j = 0; j < cols; ++j) {
                            values[i][j] = resultSet.getString(j+1);
                        }
                        i++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0; i < label.length; i++){
                System.out.print(label[i]+"\t");
            }
            System.out.println("");

            for(int i = 0; i < values.length; i++){
                for(int j = 0;j < values[i].length;j++){
                    System.out.print(values[i][j]+"\t");
                }
                System.out.println("");
            }*/
            for(int i = 0;i < r.length;i++){
                System.out.println(r[i]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
