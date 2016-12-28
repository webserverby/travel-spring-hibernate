package com.travel.dao.impl;

import com.travel.dao.TourDao;
import com.travel.dao.AbstractDao;
import com.travel.dto.SearchTour;
import com.travel.model.Tour;
import com.travel.utility.SearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class TourDaoImpl extends AbstractDao<Integer, Tour> implements TourDao {

    @SuppressWarnings("unchecked")
    public List<Tour> listTour(SearchCriteria criteria){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("beginDate"))
                .setMaxResults(criteria.getPageSize())
                .setFirstResult(criteria.getPage() * criteria.getPageSize());
        return (List<Tour>)crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<Tour> listTourNumber(){
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("beginDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Tour> tours = (List<Tour>) criteria.list();
        return tours;
    }

    @SuppressWarnings("unchecked")
    public List<Tour> searchTours(SearchTour searchTour){
        String gorod = "Город";
        Criteria criteria = createEntityCriteria();
        if(searchTour.getBeginCostSearch()!=null){
            criteria.add(Restrictions.ge("costTour", searchTour.getBeginCostSearch()));
        }
        if(searchTour.getEndCostSearch()!=null){
            criteria.add(Restrictions.le("costTour", searchTour.getEndCostSearch()));
        }
        if(searchTour.getBeginDateSearch()!=null){
            criteria.add(Restrictions.ge("beginDate", searchTour.getBeginDateSearch()));
        }
        if(searchTour.getEndDateSearch()!=null){
            criteria.add(Restrictions.le("endDate", searchTour.getEndDateSearch()));
        }
        if(searchTour.getSortDate()!=null){
            criteria.addOrder(Order.desc("beginDate"));
        }
        if(searchTour.getSortCost()!=null){
            criteria.addOrder(Order.asc("costTour"));
        }
        if(!Objects.equals(searchTour.getCitySearch(), gorod)){
            criteria.createCriteria("city", "city")
                    .add(Restrictions.eq("city.cityName", searchTour.getCitySearch()));
        }
        List<Tour> tours = (List<Tour>) criteria.list();
        return tours;
    }

    public void saveTour(Tour tour){
        save(tour);
    }

    public void deleteTour(Integer idTour) {
        Tour tour =  getByKey(idTour);
        delete(tour);
    }

    public Tour getTourId(Integer idTour){
        Tour tour =  getByKey(idTour);
        return tour;
    }

    public void updateTour(Tour tour){
        update(tour);
    }

    @SuppressWarnings("unchecked")
    public List<Tour> findTourNameTour(String nameTour){
        return  createEntityCriteria()
                .add(Restrictions.like("nameTour", nameTour + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

}
