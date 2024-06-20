package com.miguelgferreira.springboot_hotdogdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miguelgferreira.springboot_hotdogdelivery.domain.Client;
import com.miguelgferreira.springboot_hotdogdelivery.repository.ClientRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {
	private final ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Client> clients = this.clientRepository.findAll();
		return new ModelAndView("clients/list", "clients", clients);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Client client) {
		return new ModelAndView("clients/view", "clients", client);
	}

	@GetMapping("/new")
	public String CreateForm(@ModelAttribute Client client) {
		return "clients/form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Client client, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("clients/form", "formErrors", result.getAllErrors());
		}

		client = this.clientRepository.save(client);
		redirect.addFlashAttribute("globalMessage", "Client saved successfully");
		return new ModelAndView("redirect:/clients/{client.id}", "client.id", client.getId());
	}

	@GetMapping("/delete/{id}")
	public ModelAndView editForm(@PathVariable("id") Client client) {
		return new ModelAndView("clients/form", "client", client);
	}

	public ModelAndView deleteForm(@PathVariable("id") Long id, RedirectAttributes redirect) {
		this.clientRepository.deleteById(id);
		var clients = this.clientRepository.findAll();
		var mv = new ModelAndView("clients/list", "clients", clients);
		mv.addObject("globalMessage", "Client deleted successfully!");
		return mv;
	}
}
