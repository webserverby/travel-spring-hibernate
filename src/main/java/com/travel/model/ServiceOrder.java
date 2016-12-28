package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс реализующий доп. услуги к заказу
 *
 * @author Artem Faenko
 */
@Entity
@Table(name = "serviceorder")
public class ServiceOrder implements Serializable {
    private Integer idService;
    private String nameService;
    private BigDecimal costService;
    private String descriptionService;
    private Set<Order> orders = new HashSet<Order>(0);

    public ServiceOrder(){
    }

    @Id
    @Column(name = "id_service", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "serviceOrders")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Basic
    @Column(name = "nameService", nullable = true, length = 45)
    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    @Basic
    @Column(name = "costService", nullable = true, precision = 2)
    public BigDecimal getCostService() {
        return costService;
    }

    public void setCostService(BigDecimal costService) {
        this.costService = costService;
    }

    @Basic
    @Column(name = "descriptionService", nullable = true, length = 200)
    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceOrder that = (ServiceOrder) o;

        if (idService != that.idService) return false;
        if (nameService != null ? !nameService.equals(that.nameService) : that.nameService != null) return false;
        if (costService != null ? !costService.equals(that.costService) : that.costService != null) return false;
        if (descriptionService != null ? !descriptionService.equals(that.descriptionService) : that.descriptionService != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idService;
        result = 31 * result + (nameService != null ? nameService.hashCode() : 0);
        result = 31 * result + (costService != null ? costService.hashCode() : 0);
        result = 31 * result + (descriptionService != null ? descriptionService.hashCode() : 0);
        return result;
    }
}
