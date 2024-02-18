package br.com.tacuem.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity(name = "Usuario")
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_usuario")
	private Long id;

	@Column(nullable = false)
	@Size(min = 1, max = 70, message = "O nome do usuário deve ter no máximo 70 caracteres")
	private String nome;

	@Column(nullable = false)
	@Size(min = 1, max = 70, message = "O cargo do usuário deve ter no máximo 70 caracteres")
	private String cargo;

	@Email
	@Column(nullable = false)
	@Size(min = 1, max = 70, message = "O email do usuário deve ter no máximo 70 caracteres")
	private String email;

	@Column(nullable = false)
	@Size(min = 1, max = 70, message = "A senha do usuário deve ter no máximo 70 caracteres")
	private String senha;

	@Size(min = 1, max = 70, message = "O role do usuário deve ter no máximo 70 caracteres")
	private String role;

	private boolean isEnabled = false;

	public Usuario() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", email=" + email + ", senha=" + senha
				+ ", role=" + role + "]";
	}

}
