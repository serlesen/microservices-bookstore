package com.sergio.bookstore.service.books.repositories;

import java.util.Optional;

import com.sergio.bookstore.service.books.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByBooksId(long bookId);
}
