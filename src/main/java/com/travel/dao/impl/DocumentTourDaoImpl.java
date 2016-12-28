package com.travel.dao.impl;

import com.travel.dao.DocumentTourDao;
import com.travel.dao.AbstractDao;
import com.travel.model.DocumentTour;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentTourDaoImpl extends AbstractDao<Integer, DocumentTour> implements DocumentTourDao {

    @SuppressWarnings("unchecked")
    public List<DocumentTour> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<DocumentTour>)crit.list();
    }

    public void saveDocument(DocumentTour documentTour) {
        persist(documentTour);
    }

    public DocumentTour findById(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTour> findAllByTourId(int idTour){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("tour");
        userCriteria.add(Restrictions.eq("idTour", idTour));
        return (List<DocumentTour>)crit.list();
    }

    public void deleteById(int id) {
        DocumentTour documentTour =  getByKey(id);
        delete(documentTour);
    }

}
