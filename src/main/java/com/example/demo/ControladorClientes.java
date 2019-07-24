package com.example.demo;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.*;
import com.example.service.ClienteService;

import io.swagger.annotations.ApiOperation;

import com.example.dominio.Cliente;
import com.example.dominio.Estadistica;

@RestController
public class ControladorClientes {
	//@RequestMapping("/")
	//@ResponseBody
	
	@Autowired
	@Qualifier("clienteservice")
	private ClienteService clienteservice;
	
	private final int edadEstandarFallecimientoPeru = 80;
	String home() {
		return "HolaMundo";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity obtenerClientes(@RequestParam(value = "id") long id) {
        Cliente customer = new Cliente();
        customer.setApellido("gonzales");
        customer.setNombre("enrique");
        customer.setFechaNacimiento("27061983");
        return new ResponseEntity(customer, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Insertar Clientes-Endpoint de Entrada POST /customers")
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity insertarClientes(@RequestBody Cliente cliente) {
        Cliente clienteDevuelto = clienteservice.addCliente(cliente);
        return new ResponseEntity(clienteDevuelto, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Listado de Clientes-Endpoint de salida GET /customers")
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity listarPersonas() {
        List<Cliente> listaClientes = clienteservice.listAllCliente();
        int ctaLista = 0;
        Cliente temp = null;
        String anhoCliente = "";
        Calendar cal= Calendar.getInstance();
        int anhosFaltantes = 0;
        int anhoActual= cal.get(Calendar.YEAR);
        String fechaFallecimientoProbable = "";
        while(listaClientes!=null && listaClientes.size()>ctaLista) {
        	temp = listaClientes.get(ctaLista);
        	anhoCliente = temp.getFechaNacimiento().toString().substring(0, 4);
        	int edadCliente = anhoActual - (Integer.parseInt(anhoCliente));
        	anhosFaltantes = edadEstandarFallecimientoPeru - edadCliente;
        	fechaFallecimientoProbable = (anhoActual + anhosFaltantes) + temp.getFechaNacimiento().toString().substring(4, 8);
        	listaClientes.get(ctaLista).setFechaMuerte(fechaFallecimientoProbable);
        	ctaLista++;
        }
		return new ResponseEntity(listaClientes, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Endpoint de salida GET  /customers/statistics")
	@RequestMapping(value = "/customers/statistics", method = RequestMethod.GET)
    public ResponseEntity obtenerEstadistica() {
        List<Cliente> listaClientes = clienteservice.listAllCliente();
        int ctaLista = 0;
        Cliente temp = null;
        Calendar cal= Calendar.getInstance();
        int anhoActual= cal.get(Calendar.YEAR);
        String anhoCliente = "";
        int sumaEdades = 0;
        while(listaClientes!=null && listaClientes.size()>ctaLista) {
        	temp = listaClientes.get(ctaLista);
        	anhoCliente = temp.getFechaNacimiento().toString().substring(0, 4);
        	int edadCliente = anhoActual - (Integer.parseInt(anhoCliente));
        	sumaEdades = sumaEdades + edadCliente;
        	ctaLista++;
        }
        
        int promedio = sumaEdades / listaClientes.size();
        Estadistica estadistica = new Estadistica();
        estadistica.setPromedio(promedio);
        int sumaResultado = 0;
        ctaLista  = 0;
        temp = null;
        while(listaClientes!=null && listaClientes.size()>ctaLista) {
        	temp = listaClientes.get(ctaLista);
        	anhoCliente = temp.getFechaNacimiento().toString().substring(0, 4);
        	int edadCliente = anhoActual - (Integer.parseInt(anhoCliente));
        	int varianza = edadCliente - promedio;
        	int resultado = (int) Math.pow(varianza, 2);
        	sumaResultado = sumaResultado + resultado; 
        	ctaLista++;
        }
        double suma = sumaResultado / listaClientes.size(); 
        
        float desviacionEstandar = (float)Math.sqrt(suma);
        estadistica.setDesviacionEstandar(desviacionEstandar);
        return new ResponseEntity(estadistica, HttpStatus.OK);
    }
	
	
}
