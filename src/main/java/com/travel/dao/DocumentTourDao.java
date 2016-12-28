package com.travel.dao;

import com.travel.model.DocumentTour;

import java.util.List;

public interface DocumentTourDao {

    List<DocumentTour> findAll();

    DocumentTour findById(int id);

    void saveDocument(DocumentTour documentTour);

    List<DocumentTour> findAllByTourId(int idTour);

    void deleteById(int id);

}
