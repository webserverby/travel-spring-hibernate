package com.travel.dao.impl;

import com.travel.dao.ServiceOrderDao;
import com.travel.dao.AbstractDao;
import com.travel.model.ServiceOrder;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceOrderDaoImpl extends AbstractDao<Integer, ServiceOrder> implements ServiceOrderDao {

    public ServiceOrder findById(int idService) {
        return getByKey(idService);
    }

    @SuppressWarnings("unchecked")
    public List<ServiceOrder> findAll(){
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<ServiceOrder>)crit.list();
    }

}
