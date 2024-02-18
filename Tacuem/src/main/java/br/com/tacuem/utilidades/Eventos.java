package br.com.tacuem.utilidades;

import org.springframework.context.ApplicationEvent;
import br.com.tacuem.model.Usuario;

public class Eventos extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private String confirmaUrl;

	public Eventos(Usuario usuario, String confirmaUrl) {
		super(usuario);
		this.usuario = usuario;
		this.confirmaUrl = confirmaUrl;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaUrl() {
		return confirmaUrl;
	}

	public void setConfirmaUrl(String confirmaUrl) {
		this.confirmaUrl = confirmaUrl;
	}

}
