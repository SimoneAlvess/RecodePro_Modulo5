package com.destinofacil.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "promocoes")
public class Promocao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_promocao")
	private Long idPromocao;
	
	@Column(nullable = false)
	private Integer desconto;
	
	@Column(name = "validade_promocao")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime validadePromocao;

	@Column(nullable = false, length = 128)
	private String pacote;

}
