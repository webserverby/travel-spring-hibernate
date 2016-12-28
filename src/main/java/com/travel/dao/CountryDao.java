package com.travel.dao;

import com.travel.model.Country;

import java.util.List;

public interface CountryDao {

    List<Country> findAll();

    Country findById(int idCountry);

    void save(Country country);

    List<Country> findAllCountry(String countryName);

    List<Country> findId(int idCountry);

    List<Country> findAllTouryId(int idTour);

    void deleteById(int id);

}
