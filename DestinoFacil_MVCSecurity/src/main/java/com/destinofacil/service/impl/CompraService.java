package com.destinofacil.service.impl;

import java.util.List;

import com.destinofacil.dto.CompraDto;
import com.destinofacil.entity.Compra;

public interface CompraService {

	void salvarCompra(CompraDto compraDto);

	Compra editarCompra(Long idCompra, CompraDto compraDto);

	void deletarCompra(Long idCompra);

	List<CompraDto> findAllCompras();

	Compra findById(Long id);
}
