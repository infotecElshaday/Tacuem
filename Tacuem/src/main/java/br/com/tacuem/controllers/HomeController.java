package br.com.tacuem.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.tacuem.model.Usuario;
import br.com.tacuem.modelDto.UsuarioDTO;
import br.com.tacuem.servicos.UsuarioServicos;
import br.com.tacuem.utilidades.Eventos;
import br.com.tacuem.utilidades.UrlUtilidade;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UsuarioServicos usuarioServicos;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "tacuem/login";
	}

	@RequestMapping(value = "aluno", method = RequestMethod.GET)
	public String homeUsuario(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "discente/discente";
	}

	@RequestMapping(value = "mestre", method = RequestMethod.GET)
	public String homeAdmin(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "docente/docente";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.GET)
	public String novoUsuario(UsuarioDTO usuarioDTO, Model model) {
		model.addAttribute("usuario", new UsuarioDTO());
		return "tacuem/usuario.html";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO, HttpServletRequest request) {
		Usuario salvaUsuario = usuarioServicos.salvarUsuario(usuarioDTO);
		publisher.publishEvent(new Eventos(salvaUsuario, UrlUtilidade.getApplicationUrl(request)));
		return "tacuem/login.html";
	}

}
