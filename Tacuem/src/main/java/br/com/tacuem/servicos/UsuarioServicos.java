package br.com.tacuem.servicos;

import java.util.List;

import br.com.tacuem.model.Usuario;
import br.com.tacuem.modelDto.UsuarioDTO;


public interface UsuarioServicos {
	
	List<Usuario> listarUsuario();
	
	Usuario salvarUsuario (UsuarioDTO usuarioDTO);
	
	Usuario salvarUsuarioAdmin (UsuarioDTO usuarioDTO);
	
   Usuario buscarUsuario(String email);
   
   Usuario buscarUsuarioId(Long id);
   
   Usuario editarUsuario(Long id, UsuarioDTO usuarioDTO);
   
   void excluirUsuario(Long id);
   
}
