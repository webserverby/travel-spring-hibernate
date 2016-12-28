package com.travel.service.impl;

import com.travel.dao.HotelDao;
import com.travel.model.Hotel;
import com.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Transactional
    public Hotel findById(int idHotel) {
        return hotelDao.findById(idHotel);
    }

    @Transactional
    public List<Hotel> findAll() {
        return hotelDao.findAll();
    }

    @Transactional
    public List<Hotel> findAllCityId(int idCity) {
        return hotelDao.findAllCityId(idCity);
    }

    @Transactional
    public List<Hotel> findAllHotel(String hotelName){
        return hotelDao.findAllHotel(hotelName);
    }

    @Transactional
    public void save(Hotel hotel){
        hotelDao.saveHotel(hotel);
    }

    @Transactional
    public void deleteById(int id){
        hotelDao.deleteById(id);
    }

}
