package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Client;
import com.example.demo.model.ClientTakenBooks;
import com.example.demo.model.DateOfTaking;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DateOfTakingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final DateOfTakingRepository dateOfTakingRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         BookRepository bookRepository,
                         DateOfTakingRepository dateOfTakingRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.dateOfTakingRepository = dateOfTakingRepository;
    }

    @Transactional
    public void takeBookConfirm(String takenBook, Long id) {
        Book book = bookRepository.getByIsbn(takenBook);
        Client client = clientRepository.getById(id);
        client.addBook(book);
        clientRepository.save(client);
        dateOfTakingRepository.save(new DateOfTaking(id, takenBook));
    }

    public List<ClientTakenBooks> takenBooks() {
        List<ClientTakenBooks> clientTakenBooks = new ArrayList<>();
        List<Client> clients = clientRepository.findAllByOrderByIdAsc();
        for (Client client : clients) {

            for (Book book : client.getBooks()) {
                List<DateOfTaking> dates = dateOfTakingRepository.findByClientIdAndIsbn(client.getId(), book.getIsbn());
                for (DateOfTaking date : dates) {
                    clientTakenBooks.add(new ClientTakenBooks(client.getFio(), client.getBirthDate(), book.getTitle(), book.getAuthor(), book.getIsbn(), date.getTookAt()));
                }
            }
        }
        return clientTakenBooks;
    }
}
