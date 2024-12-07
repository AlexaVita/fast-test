package com.example.demo.controller;

import com.example.demo.model.ClientTakenBooks;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonRestController {
    @Autowired
    ClientService clientService;

    @GetMapping("/takenBooks")
    public List<ClientTakenBooks> takenBooks() {
        return clientService.takenBooks();
    }
}
