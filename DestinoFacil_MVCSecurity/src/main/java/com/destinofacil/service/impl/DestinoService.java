package com.destinofacil.service.impl;

import java.util.List;

import com.destinofacil.dto.DestinoDto;
import com.destinofacil.entity.Destino;

public interface DestinoService {

	void salvarDestino(DestinoDto destinoDto);

	Destino editarDestino(Long idDestino, DestinoDto destinoDto);

	void deletarDestino(Long idDestino);

	List<DestinoDto> findAllDestinos();

	Destino findById(Long id);
}
