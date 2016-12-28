package com.travel.service;

import com.travel.dto.SearchTour;
import com.travel.utility.SearchCriteria;
import com.travel.model.Tour;

import java.util.List;

public interface TourService {

    List<Tour> listTour(SearchCriteria criteria);

    List<Tour> listTourNumber();

    List<Tour> searchTours(SearchTour searchTour);

    void saveTour(Tour tour);

    void deleteTour(Integer idTour);

    Tour getTourId(Integer idTour);

    void updateTour(Tour tour);

    List<Tour> findTourNameTour(String nameTour);

}
