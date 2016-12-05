package com.buaa.study2.helper;

import com.buaa.study2.utils.CollectionUtil;
import com.buaa.study2.utils.PropUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by zgq on 2016/12/5.
 */
public final class DataBaseHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(DataBaseHelper.class);
//    private static final String DRIVER;
//    private static final String URL;
//    private static final String USERNAME;
//    private static final String PASSWORD;

    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;
    static {
        CONNECTION_HOLDER=new ThreadLocal<Connection>();
        QUERY_RUNNER=new QueryRunner();

        Properties properties= PropUtils.loadProps("config.properties");
        String driver=properties.getProperty("jdbc.driver");
        String url=properties.getProperty("jdbc.url");
        String username=properties.getProperty("jdbc.username");
        String password=properties.getProperty("jdbc.password");

        DATA_SOURCE=new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }
    public static Connection getConnection(){
        Connection conn=CONNECTION_HOLDER.get();
       if(conn==null){
           try {
               conn= DATA_SOURCE.getConnection();
           }catch (SQLException e){
               LOGGER.error("connect failure",e);
               throw new RuntimeException(e);
           }finally {
                CONNECTION_HOLDER.set(conn);
           }
       }
        return conn;
    }



    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql){
        List<T> entityList;
        Connection conn=getConnection();
        try{
            entityList=QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass));

        }catch (SQLException e){
            LOGGER.error("query List error",e);
            throw new RuntimeException(e);
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
        }
        return entity;
    }

    public static int executeUpdateEntity(String sql,Object... params){
        int rows;
        Connection conn=getConnection();
        try{
            rows=QUERY_RUNNER.update(conn,sql,params);

        }catch (SQLException e){
            LOGGER.error("update  error",e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    public static <T> boolean insertEntity(Class<T> entityClass, Map<String,Object> fieldMap){
       if(CollectionUtil.isEmpty(fieldMap)){
           LOGGER.error("insert an empty entity");
           return false;
       }
        String sql="insert into"+getTableName(entityClass);
        StringBuilder clumns=new StringBuilder("(");
        StringBuilder values=new StringBuilder("(");
        for(String fieldName:fieldMap.keySet()){
            clumns.append(fieldName).append(",");
            values.append("?,");
        }
        clumns.replace(clumns.lastIndexOf(","),clumns.length(),")");
        values.replace(values.lastIndexOf(","),values.length(),")");
        sql+=clumns+"VALUES"+values;
        Object[] params=fieldMap.values().toArray();
        return executeUpdateEntity(sql,params)==1;
    }

    public static <T> boolean updateEntity(Class<T> entityClass, Long id,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("update an empty entity");
            return false;
        }
        String sql="UPDATE"+getTableName(entityClass)+"SET";
        StringBuilder clumns=new StringBuilder("(");
        for(String fieldName:fieldMap.keySet()){
            clumns.append(fieldName).append("=?,");
        }
        sql+=clumns.substring(0,clumns.lastIndexOf(","))+"where id=?";
        List<Object> paramList=new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params=paramList.toArray();
        return executeUpdateEntity(sql,params)==1;
    }

    public static <T> boolean deleteEntity(Class<T> entityClass, Long id){
        String sql="delete from"+getTableName(entityClass)+"where id=?";
        return executeUpdateEntity(sql,id)==1;
    }

    private static String getTableName(Class<?> entityClass){
        return entityClass.getSimpleName();
    }
}
