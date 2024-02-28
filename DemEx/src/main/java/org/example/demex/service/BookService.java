package org.example.demex.service;

import lombok.RequiredArgsConstructor;
import org.example.demex.model.Book;
import org.example.demex.repo.BookRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo statementRepo;
    private final UserService userService;

    public void create(Book statement, User user){
        statement.setUser(userService.getAuthorizedUser(user));
        statement.setNameBook(statement.getNameBook());
        statement.setDescription(statement.getDescription());
        statement.setStatus("недавно добавлена");
        statementRepo.save(statement);
    }

    public List<Book> getUserStatement(User user){
        return statementRepo.findStatementByUser(userService.getAuthorizedUser(user));
    }

    public List<Book> getAllStatement(){
        return statementRepo.findAll();
    }

    public void confirmation(Book statement){
        statement.setStatus("Подтверждена");
        statementRepo.save(statement);
    }

    public void rejected(Book statement){
        statement.setStatus("Отклонена");
        statementRepo.save(statement);
    }
}
