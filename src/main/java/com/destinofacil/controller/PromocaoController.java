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

import com.destinofacil.dto.PromocaoDto;
import com.destinofacil.service.impl.DestinoService;
import com.destinofacil.service.impl.PromocaoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promocoes")
public class PromocaoController {

	@Autowired
	private DestinoService destinoService;

	@Autowired
	private PromocaoService promocaoService;

	@GetMapping("/promocoesdisponiveis")
	public ModelAndView promoList() {
		ModelAndView modelAndView = new ModelAndView("promocao");
		modelAndView.addObject("destinos", destinoService.findAllDestinos());
		return modelAndView;
	}

	@GetMapping
	public ModelAndView promocoes() {
		ModelAndView modelAndView = new ModelAndView("promocao/index");
		modelAndView.addObject("promocoes", promocaoService.findAllPromocoes());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("promocao/form");
		modelAndView.addObject("promocaoDto", new PromocaoDto());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute @Valid PromocaoDto promocaoDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("promocaoDto", promocaoDto);
			return "promocao/form";
		}

		promocaoService.salvarPromocao(promocaoDto);
		return "redirect:/promocoes";
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("promocao/form");
		modelAndView.addObject("promocaoDto", promocaoService.findById(id));
		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @ModelAttribute @Valid PromocaoDto promocaoDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("promocaoDto", promocaoDto);
			return "promocao/form";
		}

		promocaoService.editarPromocao(id, promocaoDto);
		return "redirect:/promocoes";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		promocaoService.deletarPromocao(id);
		return "redirect:/promocoes";
	}
}
