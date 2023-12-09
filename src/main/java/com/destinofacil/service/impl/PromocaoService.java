package com.destinofacil.service.impl;

import java.util.List;

import com.destinofacil.dto.PromocaoDto;
import com.destinofacil.entity.Promocao;

public interface PromocaoService {

	void salvarPromocao(PromocaoDto promocaoDto);

	Promocao editarPromocao(Long idPromocao, PromocaoDto promocaoDto);

	void deletarPromocao(Long idPromocao);

	List<PromocaoDto> findAllPromocoes();

	Promocao findById(Long id);
}
