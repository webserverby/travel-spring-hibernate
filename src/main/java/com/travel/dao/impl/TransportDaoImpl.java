package com.travel.dao.impl;

import com.travel.dao.TransportDao;
import com.travel.dao.AbstractDao;
import com.travel.model.Transport;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransportDaoImpl extends AbstractDao<Integer, Transport> implements TransportDao {

    @SuppressWarnings("unchecked")
    public List<Transport> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Transport>)crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void saveTransport(Transport transport) {
        save(transport);
    }

    public Transport findById(int idTransport) {
        return getByKey(idTransport);
    }

    @SuppressWarnings("unchecked")
    public List<Transport> findAllCityId(int idCity){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("city");
        userCriteria.add(Restrictions.eq("idCity", idCity));
        return (List<Transport>)crit.list();
    }

    @SuppressWarnings("unchecked")
    public  List<Transport> findAllTransport(String reis){
        return  createEntityCriteria()
                .add(Restrictions.like("reis", reis + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void deleteById(int id) {
        Transport transport =  getByKey(id);
        delete(transport);
    }

}
