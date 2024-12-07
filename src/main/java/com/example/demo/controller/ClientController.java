package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Client;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClientService clientService;

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("client", new Client());
        return "new-client-form";
    }

    @PostMapping("/save")
    public String saveClient(
            @Valid Client client,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs
    ) {

        if (bindingResult.hasErrors()) {
            return "new-client-form";
        }

        clientRepository.save(client);

        return "redirect:/clients/list";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable("id") Long id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            model.addAttribute("client", client);
            return "edit-client";
        } else {
            return "redirect:/clients";
        }
    }

    @GetMapping("/{id}/takeBook")
    public String takeBook(@PathVariable("id") Long id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            List<Book> books = bookRepository.findAllByOrderByIsbnAsc();
            model.addAttribute("client", client);
            model.addAttribute("books", books);
            return "take-book";
        } else {
            return "redirect:/clients";
        }
    }

    @PostMapping("/takeBook")
    public String takeBookConfirm(@RequestParam("takenBook") String takenBook,
                                  @RequestParam("id") Long id,
                                  Model model) {
        if (takenBook != null && !takenBook.isEmpty()) {
            clientService.takeBookConfirm(takenBook, id);
        }

        return "redirect:/clients/list";

    }


    @PostMapping("/update-client")
    public String updateClient(Model model,
                               @Valid Client client,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-client";
        }
        clientRepository.save(client);
        return "redirect:/clients/list";
    }

    @GetMapping("/list")
    public String listClients(Model model) {
        model.addAttribute("clients", clientRepository.findAllByOrderByIdAsc());
        return "list-clients";
    }
}
