package com.nhnacademy.delivery.book.repository;

import com.nhnacademy.delivery.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
