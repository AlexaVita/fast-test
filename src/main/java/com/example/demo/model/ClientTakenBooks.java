package com.example.demo.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ClientTakenBooks {
    private String fio;
    private LocalDate birthDate;
    private String title;
    private String author;
    private String isbn;
    private Timestamp tookAt;

    public ClientTakenBooks(String fio, LocalDate birthDate, String title, String author, String isbn, Timestamp tookAt) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.tookAt = tookAt;
    }

    public ClientTakenBooks(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
