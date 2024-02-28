package org.example.demex.controller;

import lombok.RequiredArgsConstructor;
import org.example.demex.model.Book;
import org.example.demex.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statement")
public class BookController {
    private final BookService statementService;

    @GetMapping("/user")
    public String userStatement(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("statements", statementService.getUserStatement(user));
        return "bookuser";
    }


    @GetMapping("/createForm")
    public String createStatementForm() {
        return "createbook";
    }

    @PostMapping("/create")
    public String createStatement(@AuthenticationPrincipal User user, Book statement) {
        statementService.create(statement, user);
        return "redirect:/statement/user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminStatement(Model model) {
        model.addAttribute("statements", statementService.getAllStatement());
        return "bookAdm";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/confirmation")
    public String confirmationStatement(Book statement){
        statementService.confirmation(statement);
        return "redirect:/statement/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/rejected/{statementId}")
    public String rejectedStatement(@PathVariable ("statementId") Book statement){
        statementService.rejected(statement);
        return "redirect:/statement/admin";
    }
}
