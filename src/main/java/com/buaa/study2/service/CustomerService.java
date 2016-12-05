package com.buaa.study2.service;

import com.buaa.study2.helper.DataBaseHelper;
import com.buaa.study2.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Map;

/**
 * Created by zgq on 2016/12/3.
 */
public class CustomerService {
    private static final Logger LOGGER= LoggerFactory.getLogger(DataBaseHelper.class);

    public List<Customer> getCustomerList(){
        String sql="SELECT * FROM customer";
        return DataBaseHelper.queryEntityList(Customer.class,sql);
      /*  Connection connection=null;
        List <Customer> list=new ArrayList<Customer>();
        try {
            String sql="SELECT * FROM customer";
            connection= DataBaseHelper.getConnection();
           *//* PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Customer customer=new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
                customer.setRemack(resultSet.getString("remark"));
                list.add(customer);
            }*//*
            return DataBaseHelper.queryEntityList(Customer.class,sql,connection);
        }finally {
            DataBaseHelper.closeConnection(connection);
        }*/
    }
    public Customer getCustomer(int id){
        return null;
    }
    public boolean creatCustomer(Map<String,Object> fieldMap){
        return false;
    }
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        return false;
    }
    public boolean deleteCustomer(long id){
        return false;
    }
}
