package com.travel.service.impl;

import com.travel.dao.TransportDao;
import com.travel.model.Transport;
import com.travel.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportDao transportDao;

    @Transactional
    public Transport findById(int idTransport) {
        return transportDao.findById(idTransport);
    }

    @Transactional
    public List<Transport> findAll() {
        return transportDao.findAll();
    }

    @Transactional
    public List<Transport> findAllCityId(int idCity) {
        return transportDao.findAllCityId(idCity);
    }

    @Transactional
    public List<Transport> findAllTransport(String reis){
        return transportDao.findAllTransport(reis);
    }

    @Transactional
    public void save(Transport transport){
        transportDao.saveTransport(transport);
    }

    @Transactional
    public void deleteById(int id){
        transportDao.deleteById(id);
    }

}
