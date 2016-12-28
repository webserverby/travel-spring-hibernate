package com.travel.service;

import com.travel.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll();

    Hotel findById(int idHotel);

    void save(Hotel hotel);

    List<Hotel> findAllHotel(String hotelName);

    List<Hotel> findAllCityId(int idCity);

    void deleteById(int id);

}
