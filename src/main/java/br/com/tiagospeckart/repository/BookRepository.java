package br.com.tiagospeckart.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagospeckart.models.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
	
	Optional<Book> findBookById(UUID id);
}
