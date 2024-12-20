package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.ClientTakenBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByOrderByIdAsc();

}
