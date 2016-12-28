package com.travel.dao.impl;

import com.travel.dao.HotelDao;
import com.travel.dao.AbstractDao;
import com.travel.model.Hotel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelDaoImpl extends AbstractDao<Integer, Hotel> implements HotelDao {

    @SuppressWarnings("unchecked")
    public List<Hotel> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Hotel>)crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void saveHotel(Hotel hotel) {
        save(hotel);
    }

    public Hotel findById(int idHotel) {
        return getByKey(idHotel);
    }

    @SuppressWarnings("unchecked")
    public List<Hotel> findAllCityId(int idCity){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("city");
        userCriteria.add(Restrictions.eq("idCity", idCity));
        return (List<Hotel>)crit.list();
    }

    @SuppressWarnings("unchecked")
    public  List<Hotel> findAllHotel(String hotelName){
        return  createEntityCriteria()
                .add(Restrictions.like("hotelName", hotelName + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void deleteById(int id) {
        Hotel hotel =  getByKey(id);
        delete(hotel);
    }

}
