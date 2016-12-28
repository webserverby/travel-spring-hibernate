package com.travel.service;

import com.travel.model.Transport;

import java.util.List;

public interface TransportService {

    List<Transport> findAll();

    Transport findById(int idTransport);

    void save(Transport transport);

    List<Transport> findAllTransport(String reis);

    List<Transport> findAllCityId(int idCity);

    void deleteById(int id);

}
