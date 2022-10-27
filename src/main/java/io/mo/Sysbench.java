package io.mo;

import io.mo.conn.ConnectionOperation;
import io.mo.util.SysbenchConfUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

public class Sysbench {
    public static String db_name = SysbenchConfUtil.getSysbenchDb();
    public static String tbl_prefix = SysbenchConfUtil.getSysbenchTablePrefix();
    public static int tbl_conut = SysbenchConfUtil.getSysbenchTableCount();
    public static int tbl_size = SysbenchConfUtil.getSysbenchTableSize();
    
    public static String auto_incr = SysbenchConfUtil.getSysbenchAutoIncrement(); 
    
    public static Random random = new Random();

    private static Logger LOG = Logger.getLogger(Sysbench.class.getName());
    
    public static void main(String[] args){
        if(args.length == 1){
            if(args[0] != null){
                tbl_conut = Integer.parseInt(args[0]);
            }
        }

        if(args.length == 2){
            if(args[0] != null){
                tbl_conut = Integer.parseInt(args[0]);
            }

            if(args[1] != null){
                tbl_size = Integer.parseInt(args[1]);
            }
        }
        
        String db_drop_ddl = "DROP DATABASE IF EXISTS `" + db_name +"`";

        String db_create_ddl = "CREATE DATABASE IF NOT EXISTS `" + db_name +"`";
        
        String tbl_create_ddl = "CREATE TABLE IF NOT EXISTS`tablename` (\n" +
                "`id` INT NOT NULL,\n" +
                "`k` INT DEFAULT 0,\n" +
                "`c` CHAR(120) DEFAULT NULL,\n" +
                "`pad` CHAR(60) DEFAULT NULL ,\n" +
                "PRIMARY KEY (`id`)\n" +
                ")";
        String tbl_create_auto_ddl = " CREATE TABLE IF NOT EXISTS `tablename` (\n" +
                "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                "`k` INT DEFAULT 0,\n" +
                "`c` CHAR(120) DEFAULT NULL ,\n" +
                "`pad` CHAR(60) DEFAULT NULL ,\n" +
                "PRIMARY KEY (`id`)\n" +
                ")";

        String insert_dml = "INSERT INTO `tablename` VALUES(?,?,?,?)";
        String insert_auto_dml = "INSERT INTO `tablename`(`k`,`c`,`pad`) VALUES(?,?,?)";

        try {
            Connection con = ConnectionOperation.getConnection();
            Statement stmt = con.createStatement();
            PreparedStatement insert_stmt;
            //create db and tables;
            LOG.info(String.format("Now start to initialize sysbench data, db=%s, tableCount=%d, tableSize=%d",db_name,tbl_conut,tbl_size));
            stmt.execute(db_drop_ddl);
            stmt.execute(db_create_ddl);
            stmt.execute("use `" + db_name + "`");
            for(int i = 1; i < tbl_conut + 1 ; i++){
                if(auto_incr.equalsIgnoreCase("true")){
                    String tbl_name = tbl_prefix + i;
                    LOG.info(String.format("Initialize table %s and load %d data.....",tbl_name,tbl_size));
                    stmt.execute(tbl_create_auto_ddl.replace("tablename",tbl_name));
                    
                    //batch insert
                    String sql = insert_auto_dml.replace("tablename",tbl_name);
                    insert_stmt = con.prepareStatement(sql);
                    long start = System.currentTimeMillis();
                    for(int j = 1 ; j < tbl_size + 1; j++){
                        insert_stmt.setInt(1,getRandom4Number());
                        insert_stmt.setString(2,getRandomChar(120));
                        insert_stmt.setString(3,getRandomChar(60));
                        insert_stmt.addBatch();
                        if(j % 1000 == 0){
                            insert_stmt.executeBatch();
                        }
                    }
                    
                    if(tbl_size % 1000 != 0){
                        insert_stmt.executeBatch();
                    }
                    long end = System.currentTimeMillis();
                    LOG.info(String.format("Table %s has been initialized completely, and cost:%s s",tbl_name,(end-start)/1000));

                    insert_stmt.close();
                    
                }else {
                    String tbl_name = tbl_prefix + i;
                    LOG.info(String.format("Initialize table %s and load %d data.....",tbl_name,tbl_size));
                    stmt.execute(tbl_create_ddl.replace("tablename",tbl_name));
                    //batch insert
                    String sql = insert_dml.replace("tablename",tbl_name);
                    insert_stmt = con.prepareStatement(sql);
                    long start = System.currentTimeMillis();
                    for(int j = 1 ; j < tbl_size + 1; j++){
                        insert_stmt.setInt(1,j);
                        insert_stmt.setInt(2,getRandom4Number());
                        insert_stmt.setString(3,getRandomChar(100));
                        insert_stmt.setString(4,getRandomChar(60));
                        insert_stmt.addBatch();
                        if(j % 100 == 0){
                            insert_stmt.executeBatch();
                        }
                    }

                    if(tbl_size % 1000 != 0){
                        insert_stmt.executeBatch();
                    }

                    long end = System.currentTimeMillis();

                    LOG.info(String.format("Table %s has been initialized completely, and cost:%s s",tbl_name,(end-start)/1000));

                    insert_stmt.close();
                }
            }
            LOG.info(String.format("Finished to initialize sysbench data, db=%s, tableCount=%d, tableSize=%d",db_name,tbl_conut,tbl_size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int getRandom4Number(){
        return random.nextInt(9000)+1000;
    }
    
    public static String getRandomChar(int len){
        String[] chars = new String[] { "0","1", "2", "3", "4", "5", "6", "7", "8", "9" };
        int count = len/11;
        Random r = new Random();
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");;
        for(int j = 0; j < count; j++) {
            for (int i = 0; i < 11; i++) {
                int index = r.nextInt(10);
                shortBuffer.append(chars[index]);
            }
            if( j != count -1)
                shortBuffer.append("-");
        }
        return shortBuffer.toString();
    }
    
}
