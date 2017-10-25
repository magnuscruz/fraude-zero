package br.gov.dataprev.fraudezero.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.gov.dataprev.fraudezero.service.model.ProbabilidadeFraude;
import br.gov.dataprev.fraudezero.service.util.Util;

@Path("/beneficio")
public class FraudeBeneficioService {
	
	@GET
	@Path("{nit}")
	@Produces("application/json; charset=UTF-8")
	public ProbabilidadeFraude calcularProbabilidadeFraude(@PathParam("nit") String nit) {
		ProbabilidadeFraude probabilidade = new ProbabilidadeFraude();
		probabilidade.setNit(nit);
		probabilidade.setPercentualProbabilidade(Util.random());
		probabilidade.setCriterios(Util.mockCriterios());
		return probabilidade;
	}
}
