package com.destinofacil.dto;

import java.math.BigDecimal;

import com.destinofacil.entity.Promocao;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DestinoDto {

	private Long idDestino;
	@NotEmpty (message = "O destino não deve ser vazio!")
	private String destino;
	@NotNull (message = "O preço não deve ser vazio!")
	private BigDecimal preco;
	private BigDecimal precoTotal;
	@NotNull (message = "A promoção não deve ser vazia!")
	private Promocao promocao;
	@NotEmpty (message = "O transporte não deve ser vazio!")
	private String transporte;
	@NotEmpty (message = "A imagem não deve ser vazia!")
	private String urlImagem;

}
