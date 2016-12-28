package com.travel.service;

import com.travel.model.DocumentClient;

import java.util.List;

public interface DocumentService {

    List<DocumentClient> findAll();

    DocumentClient findById(int id);

    void saveDocument(DocumentClient document);

    List<DocumentClient> findAllByUserId(int idClient);

    void deleteById(int id);

}
