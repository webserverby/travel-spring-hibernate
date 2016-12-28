package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс реализующий клиента
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="client")
public class Client implements Serializable {
    private Integer idClient;
    private String nameRu;
    private String nameEn;
    private String phoneMobile;
    private String mail;
    private String address;
    private Date birthDate;
    private String personSex;
    private String seriesNumber;
    private String received;
    private String issueDate;
    private String expiryDate;
    private Date regDate;
    private Set<Order> orders = new HashSet<Order>();
    private Set<DocumentClient> documentClients = new HashSet<DocumentClient>();

    public Client(){
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    public Set<DocumentClient> getDocumentClients() {
        return documentClients;
    }

    public void setDocumentClients(Set<DocumentClient> documentClients) {
        this.documentClients = documentClients;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Id
    @Column(name = "id_client", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "nameRu", nullable = false, length = 64)
    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Basic
    @Column(name = "nameEn", nullable = true, length = 64)
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "phoneMobile", nullable = true, length = 45)
    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 45)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 64)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "birthDate", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "personSex", nullable = true, length = 45)
    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    @Basic
    @Column(name = "seriesNumber", nullable = true, length = 45)
    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    @Basic
    @Column(name = "received", nullable = true, length = 45)
    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    @Basic
    @Column(name = "issueDate", nullable = true, length = 45)
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @Basic
    @Column(name = "expiryDate", nullable = true, length = 45)
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Basic
    @Column(name = "regDate", nullable = true, length = 45)
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (idClient != client.idClient) return false;
        if (nameRu != null ? !nameRu.equals(client.nameRu) : client.nameRu != null) return false;
        if (nameEn != null ? !nameEn.equals(client.nameEn) : client.nameEn != null) return false;
        if (phoneMobile != null ? !phoneMobile.equals(client.phoneMobile) : client.phoneMobile != null) return false;
        if (mail != null ? !mail.equals(client.mail) : client.mail != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        if (birthDate != null ? !birthDate.equals(client.birthDate) : client.birthDate != null) return false;
        if (personSex != null ? !personSex.equals(client.personSex) : client.personSex != null) return false;
        if (seriesNumber != null ? !seriesNumber.equals(client.seriesNumber) : client.seriesNumber != null)
            return false;
        if (received != null ? !received.equals(client.received) : client.received != null) return false;
        if (issueDate != null ? !issueDate.equals(client.issueDate) : client.issueDate != null) return false;
        if (expiryDate != null ? !expiryDate.equals(client.expiryDate) : client.expiryDate != null) return false;
        if (regDate != null ? !regDate.equals(client.regDate) : client.regDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (phoneMobile != null ? phoneMobile.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (personSex != null ? personSex.hashCode() : 0);
        result = 31 * result + (seriesNumber != null ? seriesNumber.hashCode() : 0);
        result = 31 * result + (received != null ? received.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        return result;
    }


}
