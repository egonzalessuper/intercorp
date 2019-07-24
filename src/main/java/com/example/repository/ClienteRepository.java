package com.example.repository;

import org.springframework.stereotype.Repository;
import java.io.Serializable;
import com.example.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


  @Repository("clienterepository") 
  public interface ClienteRepository extends JpaRepository<Cliente,Serializable>{
  
  }
 