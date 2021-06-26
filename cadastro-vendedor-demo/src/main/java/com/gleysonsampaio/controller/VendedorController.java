package com.gleysonsampaio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gleysonsampaio.model.Vendedor;
import com.gleysonsampaio.repository.VendedorRepository;

@Component
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView view = new ModelAndView("listar.html");
		List<Vendedor> vendedor = vendedorRepository.findAll();

		view.addObject("vendedores", vendedor);

		return view;
	}

	@GetMapping("/pagina-cadastro")
	public ModelAndView paginaCadastro() {
		ModelAndView view = new ModelAndView("cadastroVendedor.html");
		view.addObject("vendedorobj", new Vendedor());
		return view;

	}

	@PostMapping("**/salvar")
	public ModelAndView salvar(@Valid Vendedor vendedor, BindingResult bindingResultError) {

		if (bindingResultError.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastroVendedor");
			List<String> listErros = new ArrayList<>();

			
			for (FieldError erros : bindingResultError.getFieldErrors()) {
				String mensagem = messageSource.getMessage(erros,LocaleContextHolder.getLocale());
				listErros.add(mensagem);
			}

			modelAndView.addObject("mensagemerros", listErros);
			modelAndView.addObject("vendedorobj", vendedor);

			return modelAndView;

		}

		vendedorRepository.save(vendedor);

		ModelAndView view = new ModelAndView("listar");
		view.addObject("vendedores", vendedorRepository.findAll());
		view.addObject("vendedorobj", new Vendedor());

		return view;

	}

	@GetMapping("/editar/{codigo}")
	public ModelAndView atualizar(@PathVariable Long codigo) {
		ModelAndView modelAndView = new ModelAndView("cadastroVendedor.html");

		Vendedor vendedorEncontrado = vendedorRepository.findById(codigo).get();

		modelAndView.addObject("vendedorobj", vendedorEncontrado);

		return modelAndView;
	}

	@GetMapping("/excluir/{codigo}")
	public String deletar(@PathVariable Long codigo) {
		vendedorRepository.deleteById(codigo);

		return "redirect:/vendedor/listar";

	}

	@PostMapping("**/pesquisa")
	public ModelAndView pesquisar(@RequestParam("nome") String nome) {
		ModelAndView view = new ModelAndView("listar.html");

		view.addObject("vendedores", vendedorRepository.findByNome(nome));

		return view;
	}

}
