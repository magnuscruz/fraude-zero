package br.gov.dataprev.sibeweb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProbabilidadeFraude {
	private String nit;
	private int percentualProbabilidade;
	private List<Criterio> criterios = new ArrayList<Criterio>();

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public int getPercentualProbabilidade() {
		return percentualProbabilidade;
	}

	public void setPercentualProbabilidade(int percentualProbabilidade) {
		this.percentualProbabilidade = percentualProbabilidade;
	}

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}

}
