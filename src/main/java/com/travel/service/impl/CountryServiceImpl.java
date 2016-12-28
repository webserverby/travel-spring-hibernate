package com.travel.service.impl;

import com.travel.dao.CountryDao;
import com.travel.model.Country;
import com.travel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Transactional
    public Country findById(int idCountry) {
        return countryDao.findById(idCountry);
    }

    @Transactional
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Transactional
    public List<Country> findAllCountry(String countryName){
        return countryDao.findAllCountry(countryName);
    }

    @Transactional
    public List<Country> findId(int idCountry){
        return countryDao.findId(idCountry);
    }

    @Transactional
    public List<Country> findAllTouryId(int idTour) {
        return countryDao.findAllTouryId(idTour);
    }

    @Transactional
    public void save(Country country){
        countryDao.save(country);
    }

    @Transactional
    public void deleteById(int id){
        countryDao.deleteById(id);
    }


}
