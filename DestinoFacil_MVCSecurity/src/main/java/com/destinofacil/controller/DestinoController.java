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

import com.destinofacil.dto.DestinoDto;
import com.destinofacil.repository.PromocaoRepository;
import com.destinofacil.service.impl.DestinoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/destinos")
public class DestinoController {
	@Autowired
	private DestinoService destinoService;

	@Autowired
	private PromocaoRepository promocaoRepository;

	@GetMapping
	public ModelAndView listagem() {
		ModelAndView modelAndView = new ModelAndView("destino/destinos");

		modelAndView.addObject("destinos", destinoService.findAllDestinos());

		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("destino/formulario");
		modelAndView.addObject("destinoDto", new DestinoDto());
		modelAndView.addObject("promocao", promocaoRepository.findAll());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute @Valid DestinoDto destinoDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("destinoDto", destinoDto);
			model.addAttribute("promocao", promocaoRepository.findAll());
			return "destino/formulario";
		}

		destinoService.salvarDestino(destinoDto);
		return "redirect:/destinos";
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("destino/formulario");
		modelAndView.addObject("destinoDto", destinoService.findById(id));
		modelAndView.addObject("promocao", promocaoRepository.findAll());
		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @ModelAttribute @Valid DestinoDto destinoDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("destinoDto", destinoDto);
			model.addAttribute("promocao", promocaoRepository.findAll());
			return "destino/formulario";
		}

		destinoService.editarDestino(id, destinoDto);
		return "redirect:/destinos";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		destinoService.deletarDestino(id);
		return "redirect:/destinos";
	}
}
