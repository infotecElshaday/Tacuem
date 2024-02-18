package br.com.tacuem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import br.com.tacuem.utilidades.ExpiracaoToken;

@Entity(name = "Token")
@Table(name = "token", uniqueConstraints = @UniqueConstraint(columnNames = "token"))
@SequenceGenerator(name = "seq_token", sequenceName = "seq_token", allocationSize = 1, initialValue = 1)
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_token")
	private Long id;

	@Column(nullable = false)
	@Size(min = 1, max = 70, message = "O nome do token deve ter no máximo 70 caracteres")
	private String token;

	@Column(nullable = false)
	private Date expiracao;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Token(String token, Usuario usuario) {
		this.token = token;
		this.usuario = usuario;
		this.expiracao = ExpiracaoToken.tempoExpiracao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiracao() {
		return expiracao;
	}

	public void setExpiracao(Date expiracao) {
		this.expiracao = expiracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
