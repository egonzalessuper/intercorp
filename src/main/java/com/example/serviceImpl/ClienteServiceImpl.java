package com.example.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.dominio.Cliente;
import com.example.repository.ClienteRepository;
import com.example.service.ClienteService;

@Service("clienteservice")
public class ClienteServiceImpl implements ClienteService{
	@Autowired
	@Qualifier("clienterepository")
	private ClienteRepository clienterepository;
	
	@Override
	public List<Cliente> listAllCliente(){
		return clienterepository.findAll();
	}
	
	@Override
	public Cliente addCliente(Cliente cliente) {
		return clienterepository.save(cliente);
	}
}
