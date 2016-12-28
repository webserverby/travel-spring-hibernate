package com.travel.dao.impl;

import com.travel.dao.OrderDao;
import com.travel.dao.AbstractDao;
import com.travel.dto.SearchOrder;
import com.travel.model.Order;
import com.travel.utility.SearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

    private LocalDate today = LocalDate.now();
    private LocalDate week = today.minusWeeks(1);
    private Date todayNew = java.sql.Date.valueOf(today);
    private Date weekNew = java.sql.Date.valueOf(week);
    private LocalDate month = today.minusMonths(1);
    private Date monthNew = java.sql.Date.valueOf(month);

    @SuppressWarnings("unchecked")
    public List<Order> listOrder(SearchCriteria criteria){
        Criteria crit = createEntityCriteria();
        crit.addOrder(org.hibernate.criterion.Order.desc("orderDate"))
                .setMaxResults(criteria.getPageSize())
                .setFirstResult(criteria.getPage() * criteria.getPageSize());
        return (List<Order>)crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<Order> listOrderNumber(){
        Criteria criteria = createEntityCriteria().addOrder(org.hibernate.criterion.Order.desc("orderDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Order> orders = (List<Order>) criteria.list();
        return orders;
    }

    @SuppressWarnings("unchecked")
    public List<Order> searchOrders(SearchOrder searchOrder){

        Criteria criteria = createEntityCriteria();
        if(searchOrder.getBeginDateSearchOrder()!=null && searchOrder.getEndDateSearchOrder()!=null){
            criteria.add(Restrictions.between("orderDate", searchOrder.getBeginDateSearchOrder(), searchOrder.getEndDateSearchOrder()));
        }
        if(searchOrder.getBeginCostSearchOrder()!=null){
            criteria.add(Restrictions.ge("costOrder", searchOrder.getBeginCostSearchOrder()));
        }
        if(searchOrder.getEndCostSearchOrder()!=null){
            criteria.add(Restrictions.le("costOrder", searchOrder.getEndCostSearchOrder()));
        }
        if(searchOrder.getBeginDateSearchAvia()!=null && searchOrder.getEndDateSearchAvia()!=null){
            criteria.createCriteria("tour", "tour")
                    .add(Restrictions.between("tour.beginDate", searchOrder.getBeginDateSearchAvia(), searchOrder.getEndDateSearchAvia()));
        }
        if(searchOrder.getSortDateOrder()!=null){
            criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
        }
        if(searchOrder.getSortCostOrder()!=null){
            criteria.addOrder(org.hibernate.criterion.Order.asc("costOrder"));
        }
        if(searchOrder.getSortDateAvia()!=null){
            criteria.createCriteria("tour", "tour")
                    .addOrder(org.hibernate.criterion.Order.desc("tour.beginDate"));
        }

        List<Order> orders = (List<Order>) criteria.list();
        return orders;
    }

    @SuppressWarnings("unchecked")
    public List<Order> grafikWeekOrders(){

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.between("orderDate", weekNew, todayNew));
        criteria.addOrder(org.hibernate.criterion.Order.asc("orderDate"));
        List<Order> orders = (List<Order>) criteria.list();
        return orders;
    }

    @SuppressWarnings("unchecked")
    public List<Order> grafikMonthOrders(){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.between("orderDate", monthNew, todayNew));
        criteria.addOrder(org.hibernate.criterion.Order.asc("orderDate"));
        List<Order> orders = (List<Order>) criteria.list();
        return orders;
    }

    @SuppressWarnings("unchecked")
    public List<BigDecimal> repotsWeekOrders(){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.between("orderDate", weekNew, todayNew));
        criteria.addOrder(org.hibernate.criterion.Order.asc("orderDate"));
        criteria.setProjection(Projections.sum("costOrder"));
        List sumCost = criteria.list();
        return sumCost;
    }

    public void saveOrder(Order order){
        save(order);
    }

    public Order deleteOrder(Integer idOrder) {
        Order order = getByKey(idOrder);
        delete(order);
        return order;
    }

    public Order getOrderId(Integer idOrder){
        Order order = getByKey(idOrder);
        return order;
    }

    public void updateOrder(Order order){
        update(order);
    }

    @SuppressWarnings("unchecked")
    public List<Order> findOrderNumber(String orderNumber){
        return  createEntityCriteria()
                .add(Restrictions.like("orderNumber", orderNumber + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

}
