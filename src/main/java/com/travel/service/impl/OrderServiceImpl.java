package com.travel.service.impl;

import com.travel.dto.SearchOrder;
import com.travel.service.OrderService;
import com.travel.utility.SearchCriteria;
import com.travel.dao.OrderDao;
import com.travel.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public List<Order> listOrder(SearchCriteria criteria) {
        return orderDao.listOrder(criteria);
    }

    @Transactional
    public List<Order> listOrderNumber() {
        return orderDao.listOrderNumber();
    }

    @Transactional
    public List<Order> searchOrders(SearchOrder searchOrder) {
        return orderDao.searchOrders(searchOrder);
    }

    @Transactional
    public List<Order> grafikWeekOrders() {
        return orderDao.grafikWeekOrders();
    }

    @Transactional
    public List<BigDecimal> repotsWeekOrders()  {
        return orderDao.repotsWeekOrders();
    }

    @Transactional
    public List<Order> grafikMonthOrders() {
        return orderDao.grafikMonthOrders();
    }

    @Transactional
    public void saveOrder(Order order){
        orderDao.saveOrder(order);
    }

    @Transactional
    public Order deleteOrder(Integer idOrder) {
        Order order = getOrderId(idOrder);
        orderDao.deleteOrder(idOrder);
        return order;
    }

    @Transactional
    public Order getOrderId(Integer idOrder) {
        return orderDao.getOrderId(idOrder);
    }

    @Transactional
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    @Transactional
    public List<Order> findOrderNumber(String orderNumber){
        return orderDao.findOrderNumber(orderNumber);
    }

}
