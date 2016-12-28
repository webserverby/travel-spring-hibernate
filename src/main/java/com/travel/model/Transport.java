package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс реализующий авиаперелеты
 *
 * @author Artem Faenko
 */
@Entity
@Table(name = "transport")
public class Transport implements Serializable {
    private Integer idTransport;
    private String cityDeparture;
    private String cityArrival;
    private Date startDateAvia;
    private Date endDateAvia;
    private String timeStart;
    private String timeEnd;
    private String airportStart;
    private String reis;
    private BigDecimal costTransport;
    private String htmlAvia;
    private City city;
    private Set<Tour> tours = new HashSet<Tour>(0);

    @Id
    @Column(name = "id_transport", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Integer idTransport) {
        this.idTransport = idTransport;
    }

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", nullable = true)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transport", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @Basic
    @Column(name = "cityDeparture", nullable = true, length = 45)
    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture;
    }

    @Basic
    @Column(name = "cityArrival", nullable = true, length = 45)
    public String getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival;
    }

    @Basic
    @Column(name = "startDateAvia", nullable = true)
    public Date getStartDateAvia() {
        return startDateAvia;
    }

    public void setStartDateAvia(Date startDateAvia) {
        this.startDateAvia = startDateAvia;
    }

    @Basic
    @Column(name = "endDateAvia", nullable = true)
    public Date getEndDateAvia() {
        return endDateAvia;
    }

    public void setEndDateAvia(Date endDateAvia) {
        this.endDateAvia = endDateAvia;
    }

    @Basic
    @Column(name = "timeStart", nullable = true, length = 45)
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @Basic
    @Column(name = "timeEnd", nullable = true, length = 45)
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Basic
    @Column(name = "airportStart", nullable = true, length = 45)
    public String getAirportStart() {
        return airportStart;
    }

    public void setAirportStart(String airportStart) {
        this.airportStart = airportStart;
    }

    @Basic
    @Column(name = "reis", nullable = true, length = 45)
    public String getReis() {
        return reis;
    }

    public void setReis(String reis) {
        this.reis = reis;
    }

    @Basic
    @Column(name = "costTransport", nullable = true, precision = 2)
    public BigDecimal getCostTransport() {
        return costTransport;
    }

    public void setCostTransport(BigDecimal costTransport) {
        this.costTransport = costTransport;
    }

    @Basic
    @Column(name = "htmlAvia", nullable = true, length = 10000)
    public String getHtmlAvia() {
        return htmlAvia;
    }

    public void setHtmlAvia(String htmlAvia) {
        this.htmlAvia = htmlAvia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (idTransport != transport.idTransport) return false;
        if (cityDeparture != null ? !cityDeparture.equals(transport.cityDeparture) : transport.cityDeparture != null)
            return false;
        if (cityArrival != null ? !cityArrival.equals(transport.cityArrival) : transport.cityArrival != null)
            return false;
        if (startDateAvia != null ? !startDateAvia.equals(transport.startDateAvia) : transport.startDateAvia != null)
            return false;
        if (endDateAvia != null ? !endDateAvia.equals(transport.endDateAvia) : transport.endDateAvia != null)
            return false;
        if (timeStart != null ? !timeStart.equals(transport.timeStart) : transport.timeStart != null) return false;
        if (timeEnd != null ? !timeEnd.equals(transport.timeEnd) : transport.timeEnd != null) return false;
        if (airportStart != null ? !airportStart.equals(transport.airportStart) : transport.airportStart != null)
            return false;
        if (reis != null ? !reis.equals(transport.reis) : transport.reis != null) return false;
        if (costTransport != null ? !costTransport.equals(transport.costTransport) : transport.costTransport != null)
            return false;
        if (htmlAvia != null ? !htmlAvia.equals(transport.htmlAvia) : transport.htmlAvia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransport;
        result = 31 * result + (cityDeparture != null ? cityDeparture.hashCode() : 0);
        result = 31 * result + (cityArrival != null ? cityArrival.hashCode() : 0);
        result = 31 * result + (startDateAvia != null ? startDateAvia.hashCode() : 0);
        result = 31 * result + (endDateAvia != null ? endDateAvia.hashCode() : 0);
        result = 31 * result + (timeStart != null ? timeStart.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        result = 31 * result + (airportStart != null ? airportStart.hashCode() : 0);
        result = 31 * result + (reis != null ? reis.hashCode() : 0);
        result = 31 * result + (costTransport != null ? costTransport.hashCode() : 0);
        result = 31 * result + (htmlAvia != null ? htmlAvia.hashCode() : 0);
        return result;
    }
}
