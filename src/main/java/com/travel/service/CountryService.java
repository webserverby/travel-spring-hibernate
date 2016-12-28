package com.travel.service;

import com.travel.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findById(int idCountry);

    void save(Country country);

    List<Country> findAllCountry(String countryName);

    List<Country> findId(int idCountry);

    List<Country> findAllTouryId(int idTour);

    void deleteById(int id);

}
