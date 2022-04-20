package com.sergio.bookstore.service.batch.repositories;

import com.sergio.bookstore.service.batch.entities.BookstoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BookstoreUser, Long> {

    Optional<BookstoreUser> findByLogin(String login);
}
