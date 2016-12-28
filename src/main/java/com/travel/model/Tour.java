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
 * Класс реализующий тур
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="tour")
public class Tour implements Serializable {
    private Integer idTour;
    private String nameTour;
    private Date beginDate;
    private Date endDate;
    private Integer numberDay;
    private Integer personNumber;
    private BigDecimal costTour;
    private BigDecimal costHotel;
    private BigDecimal costAvia;
    private Country country;
    private City city;
    private Hotel hotel;
    private Transport transport;
    private Set<Order> orders = new HashSet<Order>();
    private Set<DocumentTour> documentTours = new HashSet<DocumentTour>();

    public Tour(){
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tour", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tour", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Set<DocumentTour> getDocumentTours() {
        return documentTours;
    }

    public void setDocumentTours(Set<DocumentTour> documentTours) {
        this.documentTours = documentTours;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id", nullable = true)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "city_id", nullable = true)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "hotel_id", nullable = true)
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "transport_id", nullable = true)
    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Id
    @Column(name = "id_tour", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdTour() {
        return idTour;
    }

    public void setIdTour(Integer idTour) {
        this.idTour = idTour;
    }

    @Basic
    @Column(name = "nameTour", nullable = false, length = 64)
    public String getNameTour() {
        return nameTour;
    }

    public void setNameTour(String nameTour) {
        this.nameTour = nameTour;
    }

    @Basic
    @Column(name = "beginDate", nullable = true)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "endDate", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "numberDay", nullable = true)
    public Integer getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(Integer numberDay) {
        this.numberDay = numberDay;
    }

    @Basic
    @Column(name = "personNumber", nullable = true)
    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    @Basic
    @Column(name = "costTour", nullable = true, precision = 2)
    public BigDecimal getCostTour() {
        return costTour;
    }

    public void setCostTour(BigDecimal costTour) {
        this.costTour = costTour;
    }

    @Basic
    @Column(name = "costHotel", nullable = true, precision = 2)
    public BigDecimal getCostHotel() {
        return costHotel;
    }

    public void setCostHotel(BigDecimal costHotel) {
        this.costHotel = costHotel;
    }

    @Basic
    @Column(name = "costAvia", nullable = true, precision = 2)
    public BigDecimal getCostAvia() {
        return costAvia;
    }

    public void setCostAvia(BigDecimal costAvia) {
        this.costAvia = costAvia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (idTour != tour.idTour) return false;
        if (nameTour != null ? !nameTour.equals(tour.nameTour) : tour.nameTour != null) return false;
        if (beginDate != null ? !beginDate.equals(tour.beginDate) : tour.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(tour.endDate) : tour.endDate != null) return false;
        if (numberDay != null ? !numberDay.equals(tour.numberDay) : tour.numberDay != null) return false;
        if (personNumber != null ? !personNumber.equals(tour.personNumber) : tour.personNumber != null) return false;
        if (costTour != null ? !costTour.equals(tour.costTour) : tour.costTour != null) return false;
        if (costHotel != null ? !costHotel.equals(tour.costHotel) : tour.costHotel != null) return false;
        if (costAvia != null ? !costAvia.equals(tour.costAvia) : tour.costAvia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTour;
        result = 31 * result + (nameTour != null ? nameTour.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (numberDay != null ? numberDay.hashCode() : 0);
        result = 31 * result + (personNumber != null ? personNumber.hashCode() : 0);
        result = 31 * result + (costTour != null ? costTour.hashCode() : 0);
        result = 31 * result + (costHotel != null ? costHotel.hashCode() : 0);
        result = 31 * result + (costAvia != null ? costAvia.hashCode() : 0);
        return result;
    }


}
