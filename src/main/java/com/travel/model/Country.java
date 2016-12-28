package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс реализующий страну
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="country")
public class Country implements Serializable {
    private Integer idCountry;
    private String countryName;
    private Set<City> citys = new HashSet<City>(0);
    private Set<Tour> tours = new HashSet<Tour>(0);

    public Country(){
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<City> getCitys() {
        return citys;
    }

    public void setCitys(Set<City> citys) {
        this.citys = citys;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @Id
    @Column(name = "id_country", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "countryName", unique=true, nullable = true, length = 45)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country1 = (Country) o;

        if (idCountry != country1.idCountry) return false;
        if (countryName != null ? !countryName.equals(country1.countryName) : country1.countryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }
}
