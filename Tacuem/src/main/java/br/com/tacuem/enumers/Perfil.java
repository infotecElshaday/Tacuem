package br.com.tacuem.enumers;

public enum Perfil {

	// @formatter:off
	Mestre("Mestre"),
	Aluno("Aluno");
	// @formatter:on

	private final String nome;

	private Perfil(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
