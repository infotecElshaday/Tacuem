package br.com.tacuem.servicosImpl;

import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.tacuem.model.Token;
import br.com.tacuem.model.Usuario;
import br.com.tacuem.repositorios.TokenRepositorio;
import br.com.tacuem.repositorios.UsuarioRepositorio;
import br.com.tacuem.servicos.TokenServicos;

@Service
public class TokenServicosImpl implements TokenServicos {

	@Autowired
	private TokenRepositorio tokenRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public String validarToken(String token) {
		Optional<Token> umToken = tokenRepositorio.findByToken(token);
		if (umToken.isEmpty()) {
			return "INVALIDO";
		}
		Usuario usuario = umToken.get().getUsuario();
		Calendar calendar = Calendar.getInstance();
		if ((umToken.get().getExpiracao().getTime() - calendar.getTime().getTime()) <= 0) {
			return "EXPIRADO";
		}
		usuario.setEnabled(true);
		usuarioRepositorio.save(usuario);
		return "VALIDO";
	}

	@Override
	public void salvarTokenForUsuario(Usuario usuario, String token) {
		var verificaToken = new Token(token, usuario);
		tokenRepositorio.save(verificaToken);
	}

	@Override
	public Optional<Token> findByToken(String token) {
		return tokenRepositorio.findByToken(token);
	}

	@Override
	public void excluirUsuarioToken(Long id) {
		tokenRepositorio.deleteByUsuarioId(id);
	}

}
