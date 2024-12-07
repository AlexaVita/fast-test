package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String fio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="reader_books",
    joinColumns = @JoinColumn(name="reader_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "book_isbn", referencedColumnName = "isbn"))
    private Set<Book> books= new HashSet<>();
    public Client() {
    }

    public Client(String fio, LocalDate birthDate) {
        this.fio = fio;
        this.birthDate = birthDate;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.getClients().add(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.getClients().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
