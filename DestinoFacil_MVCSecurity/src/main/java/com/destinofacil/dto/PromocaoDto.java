package com.destinofacil.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PromocaoDto {

	private Long idPromocao;
	@NotNull (message = "O desconto não deve ser vazio!")
	private Integer desconto;
	@NotNull (message = "A validade da promoção não deve ser vazia!")
	private LocalDateTime validadePromocao;
	@NotEmpty (message = "O pacote não deve ser vazio!")
	private String pacote;
	
}
