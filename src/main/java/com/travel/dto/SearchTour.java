package com.travel.dto;
import java.math.BigDecimal;
import java.sql.Date;

public class SearchTour {

    private BigDecimal beginCostSearch;
    private BigDecimal endCostSearch;
    private Date beginDateSearch;
    private Date endDateSearch;
    private String sortDate;
    private String sortCost;
    private String countrySearch;
    private String citySearch;


    public BigDecimal getBeginCostSearch() {
        return beginCostSearch;
    }

    public void setBeginCostSearch(BigDecimal beginCostSearch) {
        this.beginCostSearch = beginCostSearch;
    }

    public BigDecimal getEndCostSearch() {
        return endCostSearch;
    }

    public void setEndCostSearch(BigDecimal endCostSearch) {
        this.endCostSearch = endCostSearch;
    }

    public Date getBeginDateSearch() {
        return beginDateSearch;
    }

    public void setBeginDateSearch(Date beginDateSearch) {
        this.beginDateSearch = beginDateSearch;
    }

    public Date getEndDateSearch() {
        return endDateSearch;
    }

    public void setEndDateSearch(Date endDateSearch) {
        this.endDateSearch = endDateSearch;
    }

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    public String getSortCost() {
        return sortCost;
    }

    public void setSortCost(String sortCost) {
        this.sortCost = sortCost;
    }

    public String getCountrySearch() {
        return countrySearch;
    }

    public void setCountrySearch(String countrySearch) {
        this.countrySearch = countrySearch;
    }

    public String getCitySearch() {
        return citySearch;
    }

    public void setCitySearch(String citySearch) {
        this.citySearch = citySearch;
    }
}
