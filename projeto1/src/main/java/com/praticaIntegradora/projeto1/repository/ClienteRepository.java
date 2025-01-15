package com.praticaIntegradora.projeto1.repository;

import com.praticaIntegradora.projeto1.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
