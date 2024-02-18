package br.com.tacuem.servicosImpl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.tacuem.model.Usuario;
import br.com.tacuem.modelDto.UsuarioDTO;
import br.com.tacuem.repositorios.UsuarioRepositorio;
import br.com.tacuem.servicos.UsuarioServicos;

@Service
public class UsuarioServicosImpl implements UsuarioServicos {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		if (usuarios.isEmpty()) {
			usuario.setRole("Mestre");
		} else {
			usuario.setRole("Aluno");
		}
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario salvarUsuarioAdmin(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuario() {
		return this.usuarioRepositorio.findAll();
	}

	@Override
	public Usuario buscarUsuario(String email) {
		return this.usuarioRepositorio.findByEmail(email);
	}

	@Override
	public Usuario editarUsuario(Long id, UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id do usuário é inválido:" + id));
		Usuario emailUsuario = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());
		if (emailUsuario != null) {
			usuario.setNome(usuario.getNome());
			usuario.setCargo(usuarioDTO.getCargo());
			usuario.setEmail(usuario.getEmail());
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setRole(usuarioDTO.getRole());
			Usuario editaUsuario = usuarioRepositorio.save(usuario);
			return editaUsuario;
		}
		return null;
	}

	@Override
	public Usuario buscarUsuarioId(Long id) {
		return this.usuarioRepositorio.findById(id).get();
	}

	@Override
	public void excluirUsuario(Long id) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id do usuário é inválido:" + id));
		usuarioRepositorio.delete(usuario);
	}

}
