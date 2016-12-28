package com.travel.dto;
import java.sql.Date;

public class SearchClient {

    private Date beginDateSearch;
    private Date endDateSearch;
    private String nameSearch;
    private String phoneSearch;
    private String sortABC;
    private String sortDate;

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

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getPhoneSearch() {
        return phoneSearch;
    }

    public void setPhoneSearch(String phoneSearch) {
        this.phoneSearch = phoneSearch;
    }

    public String getSortABC() {
        return sortABC;
    }

    public void setSortABC(String sortABC) {
        this.sortABC = sortABC;
    }

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }
}
