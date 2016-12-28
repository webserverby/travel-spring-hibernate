package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс реализующий город
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="city")
public class City implements Serializable {
    private Integer idCity;
    private String cityName;
    private String coordinateX;
    private String coordinateY;
    private String locationId;
    private String codeIata;
    private Country country;
    private Set<Tour> tours = new HashSet<Tour>(0);
    private Set<Hotel> hotels = new HashSet<Hotel>(0);
    private Set<Transport> transports = new HashSet<Transport>(0);

    public City(){
    }

    @Id
    @Column(name = "id_city", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id", nullable = true)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }

    @Basic
    @Column(name = "cityName", nullable = true, length = 45)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "coordinateX", nullable = true, length = 45)
    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    @Basic
    @Column(name = "coordinateY", nullable = true, length = 45)
    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Basic
    @Column(name = "locationId", nullable = true, length = 45)
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "codeIATA", nullable = true, length = 45)
    public String getCodeIata() {
        return codeIata;
    }

    public void setCodeIata(String codeIata) {
        this.codeIata = codeIata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (idCity != city.idCity) return false;
        if (cityName != null ? !cityName.equals(city.cityName) : city.cityName != null) return false;
        if (coordinateX != null ? !coordinateX.equals(city.coordinateX) : city.coordinateX != null) return false;
        if (coordinateY != null ? !coordinateY.equals(city.coordinateY) : city.coordinateY != null) return false;
        if (locationId != null ? !locationId.equals(city.locationId) : city.locationId != null) return false;
        if (codeIata != null ? !codeIata.equals(city.codeIata) : city.codeIata != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCity;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (coordinateX != null ? coordinateX.hashCode() : 0);
        result = 31 * result + (coordinateY != null ? coordinateY.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (codeIata != null ? codeIata.hashCode() : 0);
        return result;
    }
}
