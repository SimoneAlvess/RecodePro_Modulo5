package com.destinofacil.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.destinofacil.dto.ClienteDto;
import com.destinofacil.entity.Cliente;
import com.destinofacil.service.impl.ClienteService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}

	@GetMapping("/contato")
	public String contato() {
		return "contato";
	}

	@GetMapping("/destino")
	public String destino() {
		return "destino";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		ClienteDto cliente = new ClienteDto();
		model.addAttribute("cliente", cliente);
		return "registro/register";
	}
	
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("cliente") ClienteDto clienteDto, BindingResult result,
			Model model) {
		Cliente existingCliente = clienteService.findClienteByEmail(clienteDto.getEmail());

		if (existingCliente != null && existingCliente.getEmail() != null && !existingCliente.getEmail().isEmpty()) {
			result.rejectValue("email", null, "Já existe uma conta cadastrada com o mesmo email");
		}

		if (result.hasErrors()) {
			model.addAttribute("cliente", clienteDto);
			return "registro/register";
		}

		clienteService.saveCliente(clienteDto);
		return "redirect:/register?success";
	}

}
