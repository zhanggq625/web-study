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
    }
    public Customer getCustomer(int id){
        String sql="SELECT * FROM customer where id="+id;
        return DataBaseHelper.queryEntity(Customer.class,sql);
    }
    public boolean creatCustomer(Map<String,Object> fieldMap){
        return DataBaseHelper.insertEntity(Customer.class,fieldMap);
    }
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        return DataBaseHelper.updateEntity(Customer.class,id,fieldMap);
    }
    public boolean deleteCustomer(long id){
        return DataBaseHelper.deleteEntity(Customer.class,id);
    }
}
