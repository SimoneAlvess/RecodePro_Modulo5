package com.destinofacil.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

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
@Table(name = "destinos")
public class Destino implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_destino")
	private Long idDestino;

	@Column(nullable = false, length = 128)
	private String destino;

	@Column(nullable = false)
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;

	@Column(nullable = false, length = 128)
	private String transporte;
	
	@Column(nullable = false, name = "url_imagem")
	private String urlImagem;

	@Column(nullable = false)
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal precoTotal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "promocao_id_fk", nullable = false)
	private Promocao promocao;

}
