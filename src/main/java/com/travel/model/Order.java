package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс реализующий заказ
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="orders")
public class Order implements Serializable {
    private Integer idOrder;
    private String orderNumber;
    private Date orderDate;
    private BigDecimal costOrder;
    private String costOrderString;
    private Client client;
    private Tour tour;
    private Set<ServiceOrder> serviceOrders = new HashSet<ServiceOrder>(0);

    public Order(){
    }

    @Id
    @Column(name = "id_order", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "order_serviceorder",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") })
    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }


    @Basic
    @Column(name = "orderNumber", nullable = true, length = 45)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @Column(name = "orderDate", nullable = true)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_id", nullable = true)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tour_id", nullable = true)
    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Basic
    @Column(name = "costOrder", nullable = true, precision = 2)
    public BigDecimal getCostOrder() {
        return costOrder;
    }

    public void setCostOrder(BigDecimal costOrder) {
        this.costOrder = costOrder;
    }

    @Basic
    @Column(name = "costOrderString", nullable = true, length = 100)
    public String getCostOrderString() {
        return costOrderString;
    }

    public void setCostOrderString(String costOrderString) {
        this.costOrderString = costOrderString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (idOrder != order.idOrder) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (costOrder != null ? !costOrder.equals(order.costOrder) : order.costOrder != null) return false;
        if (costOrderString != null ? !costOrderString.equals(order.costOrderString) : order.costOrderString != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (costOrder != null ? costOrder.hashCode() : 0);
        result = 31 * result + (costOrderString != null ? costOrderString.hashCode() : 0);
        return result;
    }


}
