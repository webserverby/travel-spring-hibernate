package com.travel.service.impl;


import com.travel.dao.ServiceOrderDao;
import com.travel.model.ServiceOrder;
import com.travel.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Autowired
    private ServiceOrderDao serviceOrderDao;

    @Transactional
    public ServiceOrder findById(int idService) {
        return serviceOrderDao.findById(idService);
    }

    @Transactional
    public List<ServiceOrder> findAll() {
        return serviceOrderDao.findAll();
    }

}
