package com.example.demo.repository;

import com.example.demo.model.DateOfTaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateOfTakingRepository extends JpaRepository<DateOfTaking, Long> {
    List<DateOfTaking> findByClientIdAndIsbn(Long clientId, String isbn);
}
