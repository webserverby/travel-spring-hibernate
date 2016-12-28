package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
/**
 * Класс реализующий локументы клиентов(договора, фото)
 *
 * @author Artem Faenko
 */
@Entity
@Table(name ="documentclient")
public class DocumentClient implements Serializable {
    private Integer idDocument;
    private byte[] content;
    private String description;
    private String name;
    private String type;
    private Client client;

    public DocumentClient(){

    }

    @Id
    @Column(name = "id_document", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = false)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = true)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentClient document = (DocumentClient) o;

        if (idDocument != document.idDocument) return false;
        if (!Arrays.equals(content, document.content)) return false;
        if (description != null ? !description.equals(document.description) : document.description != null)
            return false;
        if (name != null ? !name.equals(document.name) : document.name != null) return false;
        if (type != null ? !type.equals(document.type) : document.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDocument;
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }


}
