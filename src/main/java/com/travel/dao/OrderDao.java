package com.travel.dao;

import com.travel.dto.SearchOrder;
import com.travel.model.Order;
import com.travel.utility.SearchCriteria;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {

    List<Order> listOrder(SearchCriteria criteria);

    List<Order> listOrderNumber();

    List<Order> searchOrders(SearchOrder searchOrder);

    List<Order> grafikWeekOrders();

    List<Order> grafikMonthOrders();

    List<BigDecimal> repotsWeekOrders();

    void saveOrder(Order order);

    Order deleteOrder(Integer idOrder);

    Order getOrderId(Integer idOrder);

    void updateOrder(Order order);

    List<Order> findOrderNumber(String orderNumber);

}
