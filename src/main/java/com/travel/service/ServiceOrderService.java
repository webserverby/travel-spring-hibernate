package com.travel.service;

import com.travel.model.ServiceOrder;

import java.util.List;

public interface ServiceOrderService {

    List<ServiceOrder> findAll();

    ServiceOrder findById(int idService);

}
