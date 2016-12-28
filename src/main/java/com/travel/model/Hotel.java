package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс реализующий отель
 *
 * @author Artem Faenko
 */
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {
    private Integer idHotel;
    private String hotelName;
    private String descriptionHotel;
    private String servicesHotel;
    private String servicesHotelNumber;
    private Integer hotelStar;
    private BigDecimal costHotelNumber;
    private String htmlHotel;
    private City city;
    private Set<Tour> tours = new HashSet<Tour>(0);

    public Hotel() {
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @Id
    @Column(name = "id_hotel", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    @Basic
    @Column(name = "hotelName", nullable = true, length = 45)
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Basic
    @Column(name = "descriptionHotel", nullable = true, length = 5000)
    public String getDescriptionHotel() {
        return descriptionHotel;
    }

    public void setDescriptionHotel(String descriptionHotel) {
        this.descriptionHotel = descriptionHotel;
    }

    @Basic
    @Column(name = "servicesHotel", nullable = true, length = 500)
    public String getServicesHotel() {
        return servicesHotel;
    }

    public void setServicesHotel(String servicesHotel) {
        this.servicesHotel = servicesHotel;
    }

    @Basic
    @Column(name = "servicesHotelNumber", nullable = true, length = 500)
    public String getServicesHotelNumber() {
        return servicesHotelNumber;
    }

    public void setServicesHotelNumber(String servicesHotelNumber) {
        this.servicesHotelNumber = servicesHotelNumber;
    }

    @Basic
    @Column(name = "hotelStar", nullable = true)
    public Integer getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(Integer hotelStar) {
        this.hotelStar = hotelStar;
    }

    @Basic
    @Column(name = "costHotelNumber", nullable = true, precision = 2)
    public BigDecimal getCostHotelNumber() {
        return costHotelNumber;
    }

    public void setCostHotelNumber(BigDecimal costHotelNumber) {
        this.costHotelNumber = costHotelNumber;
    }

    @Basic
    @Column(name = "htmlHotel", nullable = true, length = 10000)
    public String getHtmlHotel() {
        return htmlHotel;
    }

    public void setHtmlHotel(String htmlHotel) {
        this.htmlHotel = htmlHotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (idHotel != hotel.idHotel) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        if (descriptionHotel != null ? !descriptionHotel.equals(hotel.descriptionHotel) : hotel.descriptionHotel != null)
            return false;
        if (servicesHotel != null ? !servicesHotel.equals(hotel.servicesHotel) : hotel.servicesHotel != null)
            return false;
        if (servicesHotelNumber != null ? !servicesHotelNumber.equals(hotel.servicesHotelNumber) : hotel.servicesHotelNumber != null)
            return false;
        if (hotelStar != null ? !hotelStar.equals(hotel.hotelStar) : hotel.hotelStar != null) return false;
        if (costHotelNumber != null ? !costHotelNumber.equals(hotel.costHotelNumber) : hotel.costHotelNumber != null) return false;
        if (htmlHotel != null ? !htmlHotel.equals(hotel.htmlHotel) : hotel.htmlHotel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHotel;
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (descriptionHotel != null ? descriptionHotel.hashCode() : 0);
        result = 31 * result + (servicesHotel != null ? servicesHotel.hashCode() : 0);
        result = 31 * result + (servicesHotelNumber != null ? servicesHotelNumber.hashCode() : 0);
        result = 31 * result + (hotelStar != null ? hotelStar.hashCode() : 0);
        result = 31 * result + (costHotelNumber != null ? costHotelNumber.hashCode() : 0);
        result = 31 * result + (htmlHotel != null ? htmlHotel.hashCode() : 0);
        return result;
    }
}