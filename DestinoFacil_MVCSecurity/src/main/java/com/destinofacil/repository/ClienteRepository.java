package com.destinofacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.destinofacil.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByEmail(String email);
}
