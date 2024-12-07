package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        return "new-book-form";
    }

    @PostMapping("/save")
    public String saveBook(
            @Valid Book book,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs
    ) {

        if (bindingResult.hasErrors()) {
            return "new-book-form";
        }

        bookRepository.save(book);

        return "redirect:/books/list";
    }

    @GetMapping("/edit/{isbn}")
    public String editBook(@PathVariable("isbn") String isbn, Model model) {
        Book book = bookRepository.findById(isbn).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
            return "edit-book";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/update-book")
    public String updateBook(Model model,
                             @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-book";
        }
        bookRepository.save(book);
        return "redirect:/books/list";
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAllByOrderByIsbnAsc());
        return "list-books";
    }
}
