package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private RoleDAO roleDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView cadastroUsuario(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuario/cadastro");
		modelAndView.addObject(usuario);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasFieldErrors()) {
			return cadastroUsuario(usuario);
		}

		if (usuarioDAO.checkEmail(usuario)) {
			redirectAttributes.addFlashAttribute("massage", "O email " + usuario.getEmail() + " ja esta sendo usado!");
			return new ModelAndView("redirect:/usuarios/form");
		}
		redirectAttributes.addFlashAttribute("massage", "Usuario cadastrado com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listaUsuario() {
		List<Usuario> listaUsuarios = usuarioDAO.listar();
		ModelAndView modelAndView = new ModelAndView("usuario/lista");
		modelAndView.addObject("listaUsuarios", listaUsuarios);

		return modelAndView;
	}

	@RequestMapping("/roles")
	public ModelAndView rolesAlterar(String email) {
		Usuario usuario = usuarioDAO.find(email);
		List<Role> roleList = roleDAO.roleList();
		List<String> roles = new ArrayList<String>();
		
		for (Role role : roleList) {
			roles.add(role.getNome());
		}
		ModelAndView modelAndView = new ModelAndView("usuario/roles");

		System.out.println(usuario);
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("roles", roles);
		return modelAndView;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ModelAndView rolesUpdate(Usuario usuario, RedirectAttributes redirectAttributes) {

		System.out.println(usuario);
		redirectAttributes.addFlashAttribute("massage", "Roles alterada com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}

}
