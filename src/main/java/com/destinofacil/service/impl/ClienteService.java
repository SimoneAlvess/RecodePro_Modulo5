package com.destinofacil.service.impl;

import java.util.List;

import com.destinofacil.dto.ClienteDto;
import com.destinofacil.entity.Cliente;

public interface ClienteService {

	void saveCliente(ClienteDto clienteDto);

	Cliente findClienteByEmail(String email);

	List<ClienteDto> findAllClientes();

	void deletarCliente(Long id);
	
	Cliente findById(Long id);

	Cliente editarCliente(Long id, ClienteDto clienteDto);
}
