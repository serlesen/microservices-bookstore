package com.sergio.bookstore.service.prices.respositories;

import java.util.Optional;

import com.sergio.bookstore.service.prices.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findByBookId(long bookId);
}
