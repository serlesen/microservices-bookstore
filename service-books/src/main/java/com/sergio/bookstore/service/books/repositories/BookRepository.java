package com.sergio.bookstore.service.books.repositories;

import com.sergio.bookstore.service.books.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
