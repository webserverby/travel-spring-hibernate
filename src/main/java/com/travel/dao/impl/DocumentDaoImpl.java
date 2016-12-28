package com.travel.dao.impl;

import com.travel.dao.DocumentDao;
import com.travel.dao.AbstractDao;
import com.travel.model.DocumentClient;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDaoImpl extends AbstractDao<Integer, DocumentClient> implements DocumentDao {

    @SuppressWarnings("unchecked")
    public List<DocumentClient> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<DocumentClient>)crit.list();
    }

    public void save(DocumentClient document) {
        persist(document);
    }

    public DocumentClient findById(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentClient> findAllByUserId(int idClient){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("client");
        userCriteria.add(Restrictions.eq("idClient", idClient));
        return (List<DocumentClient>)crit.list();
    }


    public void deleteById(int id) {
        DocumentClient document =  getByKey(id);
        delete(document);
    }


}
