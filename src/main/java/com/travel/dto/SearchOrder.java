package com.travel.dto;
import java.math.BigDecimal;
import java.sql.Date;

public class SearchOrder {

    private BigDecimal beginCostSearchOrder;
    private BigDecimal endCostSearchOrder;
    private Date beginDateSearchOrder;
    private Date endDateSearchOrder;
    private Date beginDateSearchAvia;
    private Date endDateSearchAvia;
    private String sortDateOrder;
    private String sortDateAvia;
    private String sortCostOrder;

    public BigDecimal getBeginCostSearchOrder() {
        return beginCostSearchOrder;
    }

    public void setBeginCostSearchOrder(BigDecimal beginCostSearchOrder) {
        this.beginCostSearchOrder = beginCostSearchOrder;
    }

    public BigDecimal getEndCostSearchOrder() {
        return endCostSearchOrder;
    }

    public void setEndCostSearchOrder(BigDecimal endCostSearchOrder) {
        this.endCostSearchOrder = endCostSearchOrder;
    }

    public Date getBeginDateSearchOrder() {
        return beginDateSearchOrder;
    }

    public void setBeginDateSearchOrder(Date beginDateSearchOrder) {
        this.beginDateSearchOrder = beginDateSearchOrder;
    }

    public Date getEndDateSearchOrder() {
        return endDateSearchOrder;
    }

    public void setEndDateSearchOrder(Date endDateSearchOrder) {
        this.endDateSearchOrder = endDateSearchOrder;
    }

    public Date getBeginDateSearchAvia() {
        return beginDateSearchAvia;
    }

    public void setBeginDateSearchAvia(Date beginDateSearchAvia) {
        this.beginDateSearchAvia = beginDateSearchAvia;
    }

    public Date getEndDateSearchAvia() {
        return endDateSearchAvia;
    }

    public void setEndDateSearchAvia(Date endDateSearchAvia) {
        this.endDateSearchAvia = endDateSearchAvia;
    }

    public String getSortDateOrder() {
        return sortDateOrder;
    }

    public void setSortDateOrder(String sortDateOrder) {
        this.sortDateOrder = sortDateOrder;
    }

    public String getSortDateAvia() {
        return sortDateAvia;
    }

    public void setSortDateAvia(String sortDateAvia) {
        this.sortDateAvia = sortDateAvia;
    }

    public String getSortCostOrder() {
        return sortCostOrder;
    }

    public void setSortCostOrder(String sortCostOrder) {
        this.sortCostOrder = sortCostOrder;
    }
}
