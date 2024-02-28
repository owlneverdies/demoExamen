package org.example.demex.repo;

import org.example.demex.model.Book;
import org.example.demex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findStatementByUser(User user);
}
