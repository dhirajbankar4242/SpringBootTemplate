package com.prime.app.module.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrudRepository extends JpaRepository<Crud, String> {
    Optional<Crud> findTopByOrderByIdDesc();
}
