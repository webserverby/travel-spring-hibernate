package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
/**
 * Класс реализующий фото отеля
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="documenttour")
public class DocumentTour {
    private Integer idDocTour;
    private byte[] contentTour;
    private String nameDoc;
    private String typeDoc;
    private Tour tour;

    @Id
    @Column(name = "id_docTour", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdDocTour() {
        return idDocTour;
    }

    public void setIdDocTour(Integer idDocTour) {
        this.idDocTour = idDocTour;
    }

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_id", nullable = true)
    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "contentTour", nullable = false)
    public byte[] getContentTour() {
        return contentTour;
    }

    public void setContentTour(byte[] contentTour) {
        this.contentTour = contentTour;
    }

    @Basic
    @Column(name = "nameDoc", nullable = false, length = 100)
    public String getNameDoc() {
        return nameDoc;
    }

    public void setNameDoc(String nameDoc) {
        this.nameDoc = nameDoc;
    }

    @Basic
    @Column(name = "typeDoc", nullable = false, length = 100)
    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentTour that = (DocumentTour) o;

        if (idDocTour != that.idDocTour) return false;
        if (!Arrays.equals(contentTour, that.contentTour)) return false;
        if (nameDoc != null ? !nameDoc.equals(that.nameDoc) : that.nameDoc != null) return false;
        if (typeDoc != null ? !typeDoc.equals(that.typeDoc) : that.typeDoc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDocTour;
        result = 31 * result + Arrays.hashCode(contentTour);
        result = 31 * result + (nameDoc != null ? nameDoc.hashCode() : 0);
        result = 31 * result + (typeDoc != null ? typeDoc.hashCode() : 0);
        return result;
    }
}
