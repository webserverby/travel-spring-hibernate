package com.travel.dao;

import com.travel.model.ServiceOrder;

import java.util.List;

public interface ServiceOrderDao {

    List<ServiceOrder> findAll();

    ServiceOrder findById(int idService);

}
