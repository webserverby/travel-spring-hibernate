package com.travel.service.impl;


import com.travel.dto.SearchTour;
import com.travel.service.TourService;
import com.travel.utility.SearchCriteria;
import com.travel.dao.TourDao;
import com.travel.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourDao tourDao;

    @Transactional
    public List<Tour> listTour(SearchCriteria criteria) {
        return tourDao.listTour(criteria);
    }

    @Transactional
    public List<Tour> listTourNumber(){
        return tourDao.listTourNumber();
    }

    @Transactional
    public List<Tour> searchTours(SearchTour searchTour){
        return tourDao.searchTours(searchTour);
    }

    @Transactional
    public void saveTour(Tour tour){
        tourDao.saveTour(tour);
    }

    @Transactional
    public void deleteTour(Integer idTour) {
        tourDao.deleteTour(idTour);
    }

    @Transactional
    public Tour getTourId(Integer idTour) {
        return tourDao.getTourId(idTour);
    }

    @Transactional
    public void updateTour(Tour tour) {
        tourDao.updateTour(tour);
    }

    @Transactional
    public List<Tour> findTourNameTour(String nameTour){
        return tourDao.findTourNameTour(nameTour);
    }


}
