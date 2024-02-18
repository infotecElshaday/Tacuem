package br.com.tacuem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tacuem.model.Usuario;
import br.com.tacuem.repositorios.UsuarioRepositorio;

@Service
public class UserDetailsServicos implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException("usuario não encontrado.");
		}
		return new UsuarioDetails(usuario);
	}
}
