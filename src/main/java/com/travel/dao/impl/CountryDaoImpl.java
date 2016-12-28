package com.travel.dao.impl;

import com.travel.dao.CountryDao;
import com.travel.dao.AbstractDao;
import com.travel.model.Country;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDaoImpl extends AbstractDao<Integer, Country> implements CountryDao {

    @SuppressWarnings("unchecked")
    public List<Country> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Country>)crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.asc("countryName")).list();
    }

    @SuppressWarnings("unchecked")
    public List<Country> findId(int idCountry) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("idCountry", idCountry));
        return (List<Country>)crit.list();
    }


    public void save(Country country) {
        save(country);
    }

    public Country findById(int idCountry) {
        return getByKey(idCountry);
    }

    @SuppressWarnings("unchecked")
    public  List<Country> findAllCountry(String countryName){
        return  createEntityCriteria()
                .add(Restrictions.like("countryName", countryName + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @SuppressWarnings("unchecked")
    public List<Country> findAllTouryId(int idTour){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("tour");
        userCriteria.add(Restrictions.eq("idTour", idTour));
        return (List<Country>)crit.list();
    }

    public void deleteById(int id) {
        Country country =  getByKey(id);
        delete(country);
    }

}
