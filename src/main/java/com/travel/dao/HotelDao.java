package com.travel.dao;

import com.travel.model.Hotel;

import java.util.List;

public interface HotelDao {

    List<Hotel> findAll();

    Hotel findById(int idHotel);

    void saveHotel(Hotel hotel);

    List<Hotel> findAllHotel(String hotelName);

    List<Hotel> findAllCityId(int idCity);

    void deleteById(int id);

}
