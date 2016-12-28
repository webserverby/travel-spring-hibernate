package com.travel.service.impl;

import com.travel.dao.DocumentTourDao;
import com.travel.model.DocumentTour;
import com.travel.service.DocumentTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentTourServiceImpl implements DocumentTourService {

    @Autowired
    private DocumentTourDao documentTourDao;

    @Transactional
    public DocumentTour findById(int id) {
        return documentTourDao.findById(id);
    }

    @Transactional
    public List<DocumentTour> findAll() {
        return documentTourDao.findAll();
    }

    @Transactional
    public List<DocumentTour> findAllByTourId(int idTour) {
        return documentTourDao.findAllByTourId(idTour);
    }

    @Transactional
    public void saveDocument(DocumentTour documentTour){
        documentTourDao.saveDocument(documentTour);
    }

    @Transactional
    public void deleteById(int id){
        documentTourDao.deleteById(id);
    }

}
