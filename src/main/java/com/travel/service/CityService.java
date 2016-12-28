package com.travel.service;

import com.travel.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findById(int idCity);

    void save(City city);

    List<City> findAllCountryId(int idCountry);

    List<City> findAllCity(String cityName);

    void deleteById(int id);

}
