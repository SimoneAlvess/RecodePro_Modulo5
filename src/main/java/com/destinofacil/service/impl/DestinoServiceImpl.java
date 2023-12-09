package com.destinofacil.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinofacil.dto.DestinoDto;
import com.destinofacil.entity.Destino;
import com.destinofacil.repository.DestinoRepository;

@Service
public class DestinoServiceImpl implements DestinoService {

	@Autowired
	private DestinoRepository destinoRepository;

	@Override
	public List<DestinoDto> findAllDestinos() {
		List<Destino> destinos = destinoRepository.findAll();
		return destinos.stream().map((destino) -> mapToDestinoDto(destino)).collect(Collectors.toList());
	}

	private DestinoDto mapToDestinoDto(Destino destino) {
		DestinoDto destinoDto = new DestinoDto();
		destinoDto.setIdDestino(destino.getIdDestino());
		destinoDto.setDestino(destino.getDestino());
		destinoDto.setTransporte(destino.getTransporte());
		destinoDto.setPreco(destino.getPreco());
		destinoDto.setPrecoTotal(destino.getPrecoTotal());
		destinoDto.setPromocao(destino.getPromocao());
		destinoDto.setUrlImagem(destino.getUrlImagem());
		return destinoDto;
	}

	@Override
	public void salvarDestino(DestinoDto destinoDto) {
	    Destino destino = new Destino();
	    destino.setDestino(destinoDto.getDestino());
	    destino.setTransporte(destinoDto.getTransporte());
	    destino.setPreco(destinoDto.getPreco());
	    destino.setPromocao(destinoDto.getPromocao());
	    destino.setUrlImagem(destinoDto.getUrlImagem());

	    if (destinoDto.getPromocao() != null) {
	        int desconto = destinoDto.getPromocao().getDesconto();
	        BigDecimal multiDesconto = BigDecimal.valueOf(1.0 - desconto / 100.0);

	        destino.setPrecoTotal(destino.getPreco().multiply(multiDesconto));
	    } else {
	        destino.setPrecoTotal(destino.getPreco());
	    }

	    destinoRepository.save(destino);
	}

	@Override
	public Destino editarDestino(Long idDestino, DestinoDto destinoDto) {
		Destino destino = destinoRepository.findById(idDestino).orElse(null);

		if (destino != null) {
			destino.setDestino(destinoDto.getDestino());
			destino.setTransporte(destinoDto.getTransporte());
			destino.setPreco(destinoDto.getPreco());
			destino.setPromocao(destinoDto.getPromocao());
			destino.setUrlImagem(destinoDto.getUrlImagem());

			if (destinoDto.getPromocao() != null) {
				int desconto = destinoDto.getPromocao().getDesconto();
				BigDecimal multiDesconto = BigDecimal.valueOf(1.0 - desconto / 100.0);

				destino.setPrecoTotal(destino.getPreco().multiply(multiDesconto));
			} else {
				destino.setPrecoTotal(destino.getPreco());
			}

			destinoRepository.save(destino);
		}

		return destino;
	}

	@Override
	public void deletarDestino(Long idDestino) {
		destinoRepository.deleteById(idDestino);
	}

	@Override
	public Destino findById(Long id) {
		 return destinoRepository.findById(id).orElse(null);
	}
}