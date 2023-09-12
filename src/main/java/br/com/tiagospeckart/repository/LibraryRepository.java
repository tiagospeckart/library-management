package br.com.tiagospeckart.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagospeckart.models.Library;

public interface LibraryRepository extends JpaRepository<Library, UUID> {

	Library findByUsername(String username);
	List<Library> findByNameContaining(String name);
}