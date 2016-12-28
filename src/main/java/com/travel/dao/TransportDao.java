package com.travel.dao;

import com.travel.model.Transport;

import java.util.List;

public interface TransportDao {

    List<Transport> findAll();

    Transport findById(int idTransport);

    void saveTransport(Transport transport);

    List<Transport> findAllTransport(String reis);

    List<Transport> findAllCityId(int idCity);

    void deleteById(int id);

}
