package com.destinofacil.dto;

import java.time.LocalDateTime;

import com.destinofacil.entity.Cliente;
import com.destinofacil.entity.Destino;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompraDto {

	private Long idCompra;
	@NotNull (message = "A data/hora da viagem não deve ser vazia!")
	private LocalDateTime dataHoraViagem;
	@NotEmpty (message = "A forma de pagamento não deve ser vazia!")
	private String formaPagamento;
	@NotNull (message = "O cliente não deve ser vazio!")
	private Cliente cliente;
	@NotNull (message = "O destino não deve ser vazio!")
	private Destino destino;
}
