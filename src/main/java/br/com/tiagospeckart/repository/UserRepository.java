package br.com.tiagospeckart.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagospeckart.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
