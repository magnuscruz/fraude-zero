package br.gov.dataprev.sibeweb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.MediaType;

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
	}
	
	public String getRisco() {
		if (probabilidadeFraude == null) {
			return "Não calculado";
		}
		
		int percent = probabilidadeFraude.getPercentualProbabilidade();
		if (percent <= 10) {
			return "Desprezível (menor que 10%)";
		}
		if (percent <= 25) {
			return "Baixo (entre 10% e 25%)";
		}
		if (percent <= 50) {
			return "Médio (entre 25% e 50%)";
		}
		if (percent <= 75) {
			return "Alto (entre 50% e 75%)";
		}
		return "É Cilada, Bino! (maior que 75%)";
	}
	
	public String getStyleDialogHeader() {
		if (probabilidadeFraude == null) {
			return "";
		}
		
		int percent = probabilidadeFraude.getPercentualProbabilidade();
		if (percent <= 10) {
			return "dialog-risco-desprezivel";
		}
		if (percent <= 25) {
			return "dialog-risco-baixo";
		}
		if (percent <= 50) {
			return "dialog-risco-medio";
		}
		if (percent <= 75) {
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
