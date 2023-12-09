package com.destinofacil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinofacil.dto.PromocaoDto;
import com.destinofacil.entity.Promocao;
import com.destinofacil.repository.PromocaoRepository;

@Service
public class PromocaoServiceImpl implements PromocaoService {

	@Autowired
	private PromocaoRepository promocaoRepository;

	@Override
	public List<PromocaoDto> findAllPromocoes() {
		List<Promocao> promocoes = promocaoRepository.findAll();
		return promocoes.stream().map((promocao) -> mapToPromocaoDto(promocao)).collect(Collectors.toList());
	}

	private PromocaoDto mapToPromocaoDto(Promocao promocao) {
		PromocaoDto promocaoDto = new PromocaoDto();
		promocaoDto.setIdPromocao(promocao.getIdPromocao());
		promocaoDto.setPacote(promocao.getPacote());
		promocaoDto.setDesconto(promocao.getDesconto());
		promocaoDto.setValidadePromocao(promocao.getValidadePromocao());

		return promocaoDto;
	}

	@Override
	public void salvarPromocao(PromocaoDto promocaoDto) {
		Promocao promocao = new Promocao();
		promocao.setPacote(promocaoDto.getPacote());
		promocao.setDesconto(promocaoDto.getDesconto());
		promocao.setValidadePromocao(promocaoDto.getValidadePromocao());

		promocaoRepository.save(promocao);
	}

	@Override
	public Promocao editarPromocao(Long idPromocao, PromocaoDto promocaoDto) {
		Promocao promocao = promocaoRepository.findById(idPromocao).orElse(null);

		if (promocao != null) {
			promocao.setPacote(promocaoDto.getPacote());
			promocao.setDesconto(promocaoDto.getDesconto());
			promocao.setValidadePromocao(promocaoDto.getValidadePromocao());
		}
		promocaoRepository.save(promocao);
		return promocao;
	}

	@Override
	public void deletarPromocao(Long idPromocao) {
		promocaoRepository.deleteById(idPromocao);
	}

	@Override
	public Promocao findById(Long id) {
		return promocaoRepository.findById(id).orElse(null);
	}

}
