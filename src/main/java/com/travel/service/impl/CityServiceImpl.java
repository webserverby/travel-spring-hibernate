package com.travel.service.impl;

import com.travel.dao.CityDao;
import com.travel.model.City;
import com.travel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Transactional
    public City findById(int idCity) {
        return cityDao.findById(idCity);
    }

    @Transactional
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Transactional
    public List<City> findAllCountryId(int idCountry) {
        return cityDao.findAllCountryId(idCountry);
    }

    @Transactional
    public List<City> findAllCity(String cityName){
        return cityDao.findAllCity(cityName);
    }

    @Transactional
    public void save(City city){
        cityDao.save(city);
    }

    @Transactional
    public void deleteById(int id){
        cityDao.deleteById(id);
    }

}
