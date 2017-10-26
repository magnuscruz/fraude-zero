package br.gov.dataprev.sibeweb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;

@ManagedBean(name = "ConcessaoBeneficioBean")
@ViewScoped
public class ConcessaoBeneficioBean implements Serializable {
	
	private String nit;
	private Cidadao cidadao;
	private ProbabilidadeFraude probabilidadeFraude;
	
	public void consultarCidadao() {
		if (nit == null || nit.isEmpty()) {
			return;
		}
		
		probabilidadeFraude = null;
		
		cidadao = CidadaoDB.findByNit(nit);
	}
	
	public void consultarRiscoFraude() {
		if (nit == null || nit.isEmpty()) {
			return;
		}
		
		probabilidadeFraude = Client
			.create()
			.resource("http://127.0.0.1:8080/fraudezero-service/fraudes/beneficio/" + nit)
			.accept(MediaType.APPLICATION_JSON)
			.get(ProbabilidadeFraude.class);
		
		String probabilidadeFraudeJson = Client
				.create()
				.resource("http://localhost:4000/?nit=1253029893")
				.accept(MediaType.TEXT_PLAIN)
				.get(String.class);
		
		double percent = new Gson().fromJson(probabilidadeFraudeJson, ProbabilidadeFraudeModelo.class).getPercentualProbabilidade();
		
		probabilidadeFraude.setPercentualProbabilidade(percent);
	}
	
	public String getRisco() {
		if (probabilidadeFraude == null) {
			return "Não calculado";
		}
		
		double percent = probabilidadeFraude.getPercentualProbabilidade();
		if (percent <= 0.1) {
			return "Desprezível (menor que 10%)";
		}
		if (percent <= 0.25) {
			return "Baixo (entre 10% e 25%)";
		}
		if (percent <= 0.5) {
			return "Médio (entre 25% e 50%)";
		}
		if (percent <= 0.75) {
			return "Alto (entre 50% e 75%)";
		}
		return "É Cilada, Bino! (maior que 75%)";
	}
	
	public String getStyleDialogHeader() {
		if (probabilidadeFraude == null) {
			return "";
		}
		
		double percent = probabilidadeFraude.getPercentualProbabilidade();
		if (percent <= 0.1) {
			return "dialog-risco-desprezivel";
		}
		if (percent <= 0.25) {
			return "dialog-risco-baixo";
		}
		if (percent <= 0.5) {
			return "dialog-risco-medio";
		}
		if (percent <= 0.75) {
			return "dialog-risco-alto";
		}
		return "dialog-risco-cilada-bino";
	}

	/////////////// GETTERS E SETTERS
	
	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public Cidadao getCidadao() {
		return cidadao;
	}
	
	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public ProbabilidadeFraude getProbabilidadeFraude() {
		return probabilidadeFraude;
	}

	public void setProbabilidadeFraude(ProbabilidadeFraude probabilidadeFraude) {
		this.probabilidadeFraude = probabilidadeFraude;
	}
}
