package br.com.tacuem.servicos;

import java.util.Optional;
import br.com.tacuem.model.Token;
import br.com.tacuem.model.Usuario;

public interface TokenServicos {

	String validarToken(String token);

	void salvarTokenForUsuario(Usuario usuario, String token);

	Optional<Token> findByToken(String token);

	void excluirUsuarioToken(Long id);
}
