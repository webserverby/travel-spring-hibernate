package com.travel.dao.impl;

import com.travel.dao.CityDao;
import com.travel.dao.AbstractDao;
import com.travel.model.City;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDaoImpl extends AbstractDao<Integer, City> implements CityDao {

    @SuppressWarnings("unchecked")
    public List<City> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<City>)crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void save(City city) {
        save(city);
    }

    public City findById(int idCity) {
        return getByKey(idCity);
    }

    @SuppressWarnings("unchecked")
    public List<City> findAllCountryId(int idCountry){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("country");
        userCriteria.add(Restrictions.eq("idCountry", idCountry));
        return (List<City>)crit.addOrder(Order.asc("cityName")).list();
    }

    @SuppressWarnings("unchecked")
    public  List<City> findAllCity(String cityName){
        return  createEntityCriteria()
                .add(Restrictions.like("cityName", cityName + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void deleteById(int id) {
        City city =  getByKey(id);
        delete(city);
    }


}
