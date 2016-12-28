package com.travel.service.impl;

import com.travel.dao.DocumentDao;
import com.travel.model.DocumentClient;
import com.travel.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;

    @Transactional
    public DocumentClient findById(int id) {
        return documentDao.findById(id);
    }

    @Transactional
    public List<DocumentClient> findAll() {
        return documentDao.findAll();
    }

    @Transactional
    public List<DocumentClient> findAllByUserId(int idClient) {
        return documentDao.findAllByUserId(idClient);
    }

    @Transactional
    public void saveDocument(DocumentClient document){
        documentDao.save(document);
    }

    @Transactional
    public void deleteById(int id){
        documentDao.deleteById(id);
    }



}
