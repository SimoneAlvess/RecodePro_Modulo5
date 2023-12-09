package com.destinofacil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinofacil.dto.CompraDto;
import com.destinofacil.entity.Compra;
import com.destinofacil.repository.CompraRepository;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Override
	public List<CompraDto> findAllCompras() {
		List<Compra> compras = compraRepository.findAll();
		return compras.stream().map((compra) -> mapToCompraDto(compra)).collect(Collectors.toList());
	}
	
	private CompraDto mapToCompraDto(Compra compra) {
		CompraDto compraDto = new CompraDto();
		compraDto.setIdCompra(compra.getIdCompra());
		compraDto.setDataHoraViagem(compra.getDataHoraViagem());
		compraDto.setFormaPagamento(compra.getFormaPagamento());
		compraDto.setCliente(compra.getCliente());
		compraDto.setDestino(compra.getDestino());
		return compraDto;
	}
	
	@Override
	public void salvarCompra(CompraDto compraDto) {
	    Compra compra = new Compra();
	    compra.setDataHoraViagem(compraDto.getDataHoraViagem());
	    compra.setFormaPagamento(compraDto.getFormaPagamento());
	    compra.setCliente(compraDto.getCliente());
	    compra.setDestino(compraDto.getDestino());
	    compraRepository.save(compra);
	}
	
	@Override
	public Compra editarCompra(Long idCompra, CompraDto compraDto) {
		Compra compra = compraRepository.findById(idCompra).orElse(null);

		if (compra != null) {
			compra.setDataHoraViagem(compraDto.getDataHoraViagem());
		    compra.setFormaPagamento(compraDto.getFormaPagamento());
		    compra.setCliente(compraDto.getCliente());
		    compra.setDestino(compraDto.getDestino());
		}
		compraRepository.save(compra);
		return compra;
	}

	@Override
	public void deletarCompra(Long idCompra) {
		compraRepository.deleteById(idCompra);
	}

	@Override
	public Compra findById(Long id) {
		return compraRepository.findById(id).orElse(null);
	}
}
