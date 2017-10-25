package br.gov.dataprev.sibeweb;

public class Criterio implements Comparable<Criterio> {
	private String nome;
	private int percentualProbabilidade;
	
	public Criterio() {}

	public Criterio(String nome) {
		this.nome = nome;
	}
	
	public Criterio(String nome, int percentualProbabilidade) {
		this.nome = nome;
		this.percentualProbabilidade = percentualProbabilidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPercentualProbabilidade() {
		return percentualProbabilidade;
	}

	public void setPercentualProbabilidade(int percentualProbabilidade) {
		this.percentualProbabilidade = percentualProbabilidade;
	}

	@Override
	public int compareTo(Criterio o) {
		return -Integer.compare(percentualProbabilidade, o.percentualProbabilidade);
	}

}
