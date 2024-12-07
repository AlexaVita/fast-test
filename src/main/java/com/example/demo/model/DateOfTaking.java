package com.example.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class DateOfTaking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private String isbn;
    @CreationTimestamp
    private Timestamp tookAt;

    public DateOfTaking() {
    }

    public DateOfTaking(Long clientId, String isbn) {
        this.clientId = clientId;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Timestamp getTookAt() {
        return tookAt;
    }

    public void setTookAt(Timestamp tookAt) {
        this.tookAt = tookAt;
    }
}
