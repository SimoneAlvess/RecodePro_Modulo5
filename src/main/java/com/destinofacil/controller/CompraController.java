package com.destinofacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.destinofacil.dto.CompraDto;
import com.destinofacil.repository.ClienteRepository;
import com.destinofacil.repository.DestinoRepository;
import com.destinofacil.service.impl.CompraService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private CompraService compraService;

	@Autowired
	private DestinoRepository destinoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ModelAndView compras() {
		ModelAndView modelAndView = new ModelAndView("compra/index");
		modelAndView.addObject("compras", compraService.findAllCompras());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("compra/form");
		modelAndView.addObject("compraDto", new CompraDto());
		modelAndView.addObject("cliente", clienteRepository.findAll());
		modelAndView.addObject("destino", destinoRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute @Valid CompraDto compraDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("compraDto", compraDto);
			model.addAttribute("cliente", clienteRepository.findAll());
			model.addAttribute("destino", destinoRepository.findAll());
			
			return "compra/form";
		}

		compraService.salvarCompra(compraDto);
		return "redirect:/compras";
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("compra/form");
		modelAndView.addObject("compraDto", compraService.findById(id));
		modelAndView.addObject("cliente", clienteRepository.findAll());
		modelAndView.addObject("destino", destinoRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @ModelAttribute @Valid CompraDto compraDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("compraDto", compraDto);
			model.addAttribute("cliente", clienteRepository.findAll());
			model.addAttribute("destino", destinoRepository.findAll());
			return "compra/form";
		}

		compraService.editarCompra(id, compraDto);
		return "redirect:/compras";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		compraService.deletarCompra(id);
		return "redirect:/compras";
	}
}
