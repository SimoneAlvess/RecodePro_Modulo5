package com.destinofacil.entity;

import java.io.Serializable;
// Importações
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "compras")
public class Compra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private Long idCompra;

	@Column(name = "data_hora_viagem")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataHoraViagem;

	@Column(nullable = false, length = 128)
	private String formaPagamento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id_fk", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "destino_id_fk", nullable = false)
	private Destino destino;

}
