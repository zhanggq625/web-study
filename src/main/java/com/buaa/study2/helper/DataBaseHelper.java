package com.buaa.study2.helper;

import com.buaa.study2.utils.PropUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 * Created by zgq on 2016/12/5.
 */
public final class DataBaseHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(DataBaseHelper.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static final ThreadLocal<Connection> CONNECTION_HOLDER=new ThreadLocal<Connection>();

    static {
        Properties properties= PropUtils.loadProps("config.properties");
        DRIVER=properties.getProperty("jdbc.driver");
        URL=properties.getProperty("jdbc.url");
        USERNAME=properties.getProperty("jdbc.username");
        PASSWORD=properties.getProperty("jdbc.password");
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            LOGGER.error("can not load file",e);
        }
    }
    public static Connection getConnection(){
        Connection conn=CONNECTION_HOLDER.get();
       if(conn==null){
           try {
               conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
           }catch (SQLException e){
               LOGGER.error("connect failure",e);
               throw new RuntimeException(e);
           }finally {
                CONNECTION_HOLDER.set(conn);
           }
       }
        return conn;
    }
    public static void closeConnection(){
        Connection conn=CONNECTION_HOLDER.get();
        if(conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                LOGGER.error("close connect failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
    private static final QueryRunner QUERY_RUNNER=new QueryRunner();


    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql){
        List<T> entityList;
        Connection conn=getConnection();
        try{
            entityList=QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass));

        }catch (SQLException e){
            LOGGER.error("query List error",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entityList;
    }
    public static <T> T queryEntity(Class<T> entityClass,String sql){
        T entity;
        Connection conn=getConnection();
        try{
            entity=QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass));

        }catch (SQLException e){
            LOGGER.error("query  error",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entity;
    }

    public static int updateEntity(String sql){
        int rows;
        Connection conn=getConnection();
        try{
            rows=QUERY_RUNNER.update(conn,sql);

        }catch (SQLException e){
            LOGGER.error("update  error",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return rows;
    }
}
