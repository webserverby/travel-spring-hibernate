package com.travel.dao;

import com.travel.model.City;

import java.util.List;

public interface CityDao {

    List<City> findAll();

    City findById(int idCity);

    void save(City city);

    List<City> findAllCity(String cityName);

    List<City> findAllCountryId(int idCountry);

    void deleteById(int id);

}
