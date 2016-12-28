package com.travel.dao;

import com.travel.model.DocumentClient;

import java.util.List;

public interface DocumentDao {

    List<DocumentClient> findAll();

    DocumentClient findById(int id);

    void save(DocumentClient document);

    List<DocumentClient> findAllByUserId(int idClient);

    void deleteById(int id);

}
