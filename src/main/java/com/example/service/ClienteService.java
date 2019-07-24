package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dominio.Cliente;

public interface ClienteService {
	
	  public abstract List<Cliente> listAllCliente();
	  public abstract Cliente addCliente(Cliente cliente);
	 
}
